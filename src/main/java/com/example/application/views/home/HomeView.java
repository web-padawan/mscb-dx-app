package com.example.application.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import com.example.application.domain.Country;
import com.example.application.domain.DataService;

@PageTitle("Home")
@Route(value = "home")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        MultiSelectComboBox<Country> comboBox = new MultiSelectComboBox<>(
                "Countries");
        comboBox.setItems(DataService.getCountries());
        comboBox.setItemLabelGenerator(Country::getName);

        getContent().setHeightFull();
        getContent().setWidthFull();
        getContent().add(comboBox);

        NativeButton groupButton = new NativeButton("Group selected items",
                event -> {
                    comboBox.setGroupSelectedItems(true);
                });

        NativeButton ungroupButton = new NativeButton("Un-group selected items",
                event -> {
                    comboBox.setGroupSelectedItems(false);
                });

        NativeButton showAllChips = new NativeButton("Show all chips",
                event -> {
                    comboBox.setAllChipsVisible(true);
                });

        NativeButton dontShowAllChips = new NativeButton("Don't show all chips",
                event -> {
                    comboBox.setAllChipsVisible(false);
                });

        getContent().add(new HorizontalLayout(groupButton, ungroupButton,
                showAllChips, dontShowAllChips));
    }
}
