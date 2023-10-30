/*
 * Copyright 2023 preed.
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
package com.reedmanit.myrestaurant.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Paul 
 * Tip = Bill Total * Tip%
 */
public class Bill {

    private Double billTotal;
    private Double tip;
    private Double billAndTipTotal;

    private Double tipPercent;
    private Double splitBetween;
    private Double splitTip;
    private Double amountPerPerson;

    private Double total;
    
    private static DecimalFormat decFormat; 
    

    public Bill() {
        
        decFormat = new DecimalFormat("0.00");
        decFormat.setRoundingMode(RoundingMode.DOWN);  
    }

    /**
     * @return the billTotal
     */
    public Double getBillTotal() {
        return billTotal;
    }

    /**
     * @param billTotal the billTotal to set
     */
    public void setBillTotal(Double billTotal) {
        this.billTotal = billTotal;
    }

    /**
     * @return the tip
     */
    public Double getTip() {
        tip = this.billTotal * this.tipPercent;
        BigDecimal bd = new BigDecimal(tip).setScale(2, RoundingMode.HALF_UP);
        tip = bd.doubleValue();
        
        return tip;
    }

    /**
     * @param tip the tip to set
     */
    public void setTip(Double tip) {
        this.tip = tip;
    }

    /**
     * @return the billAndTipTotal
     * This is the Bill Total + the Tip
     */
    public Double getBillAndTipTotal() {
        
        billAndTipTotal = this.billTotal + getTip();
        BigDecimal bd = new BigDecimal(billAndTipTotal).setScale(2, RoundingMode.HALF_UP);
        billAndTipTotal = bd.doubleValue(); 
        return billAndTipTotal;
    }

    /**
     * @param billAndTipTotal the tipTotal to set
     */
    public void setBillAndTipTotal(Double billAndTipTotal) {
        this.billAndTipTotal = billAndTipTotal;
    }

    /**
     * @return the tipPercent
     */
    public Double getTipPercent() {
        return tipPercent;
    }

    /**
     * @param tipPercent the tipPercent to set
     */
    public void setTipPercent(Double tipPercent) {
        this.tipPercent = tipPercent;
    }

    /**
     * @return the splitBetween
     */
    public Double getSplitBetween() {
        
        return splitBetween;
    }

    /**
     * @param splitBetween the splitBetween to set
     */
    public void setSplitBetween(Double splitBetween) {
        this.splitBetween = splitBetween;
    }

    /**
     * @return the amountPerPerson
     */
    public Double getAmountPerPerson() {
        amountPerPerson = this.getBillAndTipTotal() / this.getSplitBetween();
        BigDecimal bd = new BigDecimal(amountPerPerson).setScale(2, RoundingMode.HALF_UP);
        amountPerPerson = bd.doubleValue();
        return amountPerPerson;
    }

    /**
     * @param amountPerPerson the amountPerPerson to set
     */
    public void setAmountPerPerson(Double amountPerPerson) {
        this.amountPerPerson = amountPerPerson;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the splitTip
     */
    public Double getSplitTip() {
        splitTip = this.getTip() / this.getSplitBetween();
        BigDecimal bd = new BigDecimal(splitTip).setScale(2, RoundingMode.HALF_UP);
        splitTip = bd.doubleValue();
        return splitTip;
    }

}
