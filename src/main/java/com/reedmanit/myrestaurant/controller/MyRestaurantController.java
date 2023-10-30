/*
 * Copyright 2022 preed.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.reedmanit.myrestaurant.controller;

import com.reedmanit.myrestaurant.model.Bill;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author preed
 */
public class MyRestaurantController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label tipPercentLabel;
    @FXML
    private Button roundUpBtn;

    @FXML
    private Button roundDownBtn;

    @FXML
    private Button manualTipBtn;

    @FXML
    private TextField billTotalTF;

    @FXML
    private TextField tipTF;

    @FXML
    private TextField totalTipTF;

    @FXML
    private Slider tipPerctSlide;

    @FXML
    private Slider splitBetweenSlide;

    @FXML
    private Label splitBetweenLabel;

    @FXML
    private TextField splitTipTF;

    @FXML
    private TextField amountPersonTF;

    @FXML
    private Text text;

    private Bill theBill;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        theBill = new Bill();
        theBill.setTipPercent(0.1);  // iniitalise at 10%
        theBill.setSplitBetween(2.0); // initialise at split between 2 people
        theBill.setBillTotal(0.0);

        UnaryOperator<TextFormatter.Change> filter;
        filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            // Only allow a number with a decimal point be added
            public TextFormatter.Change apply(TextFormatter.Change t) {

                if (t.isReplaced()) {
                    if (t.getText().matches("[^0-9]")) {

                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));

                    }
                }

                if (t.isAdded()) {   // if something was added
                    if (t.getControlText().contains(".")) {  // if what was added contains a .

                        if (t.getText().matches("[^0-9]")) {  // if what was added not a number then

                            t.setText(""); // set what added to space
                        }
                    } else if (t.getText().matches("[^0-9.]")) { // if the text entered not a number
                        t.setText(""); // then set what was added to space
                    }
                }

                if (!t.getControlNewText().isBlank()) {  // get the text that was entered, if not blank

                    theBill.setBillTotal(Double.valueOf(t.getControlNewText())); // store it as a double in the Bill
                    totalTipTF.setText(Double.toString(theBill.getBillAndTipTotal()));  // get the bill total
                    tipTF.setText(Double.toString(theBill.getTip()));  // get the tip for the bill
                    splitTipTF.setText(Double.toString(theBill.getSplitTip()));  // get the split tip for the bill
                    amountPersonTF.setText(Double.toString(theBill.getAmountPerPerson()));  // get the amount each person pays
                }

                return t;
            }

        };

        billTotalTF.setTextFormatter(new TextFormatter<>(filter));

        tipPerctSlide.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

            if (tipPerctSlide.isValueChanging()) {
                // Do not perform any action as the value changes
            } else {
                // Perform the action as the value has been changed
                String s = Integer.toString(new_val.intValue());

                theBill.setTipPercent(new_val.doubleValue() / 100); // turn into decimal value

                tipPercentLabel.setText("Tip " + s + "%");

                totalTipTF.setText(Double.toString(theBill.getBillAndTipTotal()));  // get the bill total
                tipTF.setText(Double.toString(theBill.getTip()));  // get the tip for the bill
                splitTipTF.setText(Double.toString(theBill.getSplitTip()));  // get the split tip for the bill
                amountPersonTF.setText(Double.toString(theBill.getAmountPerPerson()));  // get the amount each person pays

            }

        });

        splitBetweenSlide.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

            String s = Integer.toString(new_val.intValue());

            splitBetweenLabel.setText("Split Between " + s);

            theBill.setSplitBetween(new_val.doubleValue());
            totalTipTF.setText(Double.toString(theBill.getBillAndTipTotal()));  // get the bill total
            tipTF.setText(Double.toString(theBill.getTip()));  // get the tip for the bill
            splitTipTF.setText(Double.toString(theBill.getSplitTip()));  // get the split tip for the bill
            amountPersonTF.setText(Double.toString(theBill.getAmountPerPerson()));  // get the amount each person pays

        });

    }

}
