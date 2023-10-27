package com.example.application.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.data.provider.ListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Set;

import com.example.application.domain.Country;
import com.example.application.domain.DataService;

@PageTitle("Home")
@Route(value = "home")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        /*
        DX test tasks:

        1. Test the default behavior:

            - select multiple items in MultiSelectComboBox or Grid
            - change MultiSelectComboBox to take all available space
            - resize the SplitLayout and observe how component works

        2. Test the new features:

            - make MultiSelectComboBox show selected items at the top
              of the overlay when it is opened
            - make MultiSelectComboBox always show chips for every
              selected item
            - try to resize the SplitLayout and to select some items
        */

        getContent().setHeightFull();
        getContent().setWidthFull();

        Grid<Country> grid = new Grid<>(Country.class, false);
        grid.setItems(DataService.getCountries());
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setMinWidth("300px");
        grid.addColumn(Country::getName).setHeader("Name");
        grid.addColumn(Country::getAbbreviation).setHeader("Abbr");

        MultiSelectComboBox<Country> comboBox = new MultiSelectComboBox<>(
                "Countries");
        comboBox.setItems(DataService.getCountries());
        comboBox.setItemLabelGenerator(Country::getName);

        grid.addSelectionListener(event -> {
            if (event.isFromClient()) {
                Set<Country> addedSelection = event.getAllSelectedItems();
                comboBox.deselectAll();
                comboBox.select(addedSelection);
            }
        });

        comboBox.addSelectionListener(event -> {
            if (event.isFromClient()) {
                Set<Country> addedSelection = event.getAllSelectedItems();
                grid.deselectAll();
                addedSelection.stream().forEach(item -> {
                    grid.select(item);
                });
            }
        });

        HorizontalLayout layout = new HorizontalLayout(comboBox);
        layout.setPadding(true);

        SplitLayout splitLayout = new SplitLayout(grid, layout);
        splitLayout.setWidthFull();
        getContent().add(splitLayout);
    }
}
