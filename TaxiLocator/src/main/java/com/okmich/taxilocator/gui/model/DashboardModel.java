/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.taxilocator.gui.model;

import com.okmich.taxilocator.FlowMediator;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author datadev
 */
public class DashboardModel extends AbstractTableModel {

    private final String[] columnNames;
    private final List<TaxiData> dataset;

    public DashboardModel() {
        this.columnNames = new String[]{
            "Taxi #", "Longitude", "Latitude", "Latest Time",
            "Total distance covered", "Total mins spent", "Current Speed"
        };
        this.dataset = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return dataset.size();
    }

    @Override
    public Class getColumnClass(int i) {
        switch (i) {
            case 0:
                return Integer.class;
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
                return Float.class;
            default:
                return String.class;
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TaxiData data = this.dataset.get(rowIndex);
        if (data != null) {
            switch (columnIndex) {
                case 0:
                    return data.getNo();
                case 1:
                    return data.getLon();
                case 2:
                    return data.getLat();
                case 3:
                    return data.getTime();
                case 4:
                    return data.getTotDist();
                case 5:
                    return data.getTotNumMins();
                case 6:
                    return data.getCurrentSpeed();
            }
        }
        return null;
    }

    public void add(TaxiData taxiData) {
        int idx = dataset.indexOf(taxiData);
        if (idx == -1) {
            dataset.add(taxiData);
        } else {
            dataset.set(idx, taxiData);
        }
        this.fireTableDataChanged();
    }

    public void add(TaxiData taxiData, FlowMediator flowMediator) {
        add(taxiData);
        flowMediator.update();
    }

}
