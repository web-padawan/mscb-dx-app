package com.example.application.views.styling;

import java.util.ArrayList;
import java.util.List;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Style Props Sample")
@Route(value = "styling", layout = MainLayout.class)
public class StylingView extends VerticalLayout {
    public StylingView() {
        /*
        DX test tasks:

        - Apply styles to the components by editing the CSS file `frontend/themes/styles.css`
        - Achieve the theme in the Figma as closely as possible using the new style properties

        Figma design:
        https://www.figma.com/file/vAkIF4h6C9skJD2C2J2eTT/Untitled?type=design&node-id=0-1&mode=design&t=TSBG6qCJecpVfXVW-0

        List of styling properties:
        https://docs.google.com/spreadsheets/d/1Mzlbd8GzSZcEgYd-odagN3CBD0TSSTFCPmNCmP8rkLU/edit?usp=sharing
        */
        Tabs tabs = new Tabs(new Tab("Lorem"), new Tab("Ipsum"), new Tab("Dolor"));
        tabs.setSelectedIndex(0);
        add(tabs);

        TextField tf = new TextField("Text Field");
        ComboBox<Person> cb = new ComboBox<>("Combo box"){{this.setItems(makePeeps());}};
        TextArea ta = new TextArea("Text Area"){{this.setHelperText("This is a text area");}};
        DatePicker dp = new DatePicker("Date picker");
        Checkbox chb = new Checkbox("Checkbox");
        HorizontalLayout fields = new HorizontalLayout(tf, cb, dp, chb);
        fields.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(fields, ta);

        Button btnDefault = new Button("Default button");
        Button btnPrimary = new Button("Primary button"){{this.addThemeVariants(ButtonVariant.LUMO_PRIMARY);}};
        Button btnTertiary = new Button("Tertiary button"){{this.addThemeVariants(ButtonVariant.LUMO_TERTIARY);}};
        HorizontalLayout buttons = new HorizontalLayout(btnDefault, btnPrimary, btnTertiary);
        add(buttons);
    }

    public static class Person {
        private String firstName;
        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public String getFirstName() {
            return this.firstName;
        }
        public String getLastName() {
            return this.lastName;
        }
        public String toString() {
            return this.firstName + " " + this.lastName;
        }
    }
    public static List<Person> makePeeps() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("John", "Smith"));
        list.add(new Person("Jane", "Doe"));
        list.add(new Person("Random", "Person"));
        return list;
    }
}
