package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import javafx.collections.transformation.FilteredList;

public class MainFormController {
    private ObservableList<CallRecord> callList = FXCollections.observableArrayList(); // список записей
    FilteredList<CallRecord> filteredCallList = new FilteredList<>(callList, p -> true);

    // таблица
    @FXML
    private TableView<CallRecord> tableView;
    @FXML
    private TableColumn<CallRecord, Integer> tvNum;
    @FXML
    private TableColumn<CallRecord, String> tvFrom;
    @FXML
    private TableColumn<CallRecord, String> tvTo;
    @FXML
    private TableColumn<CallRecord, String> tvDate;
    @FXML
    private TableColumn<CallRecord, String> tvTime;
    @FXML
    private TableColumn<CallRecord, Integer> tvDuration;

    // для добавления новй записи
    @FXML
    private TextField edNum;
    @FXML
    private TextField edFrom;
    @FXML
    private TextField edTo;
    @FXML
    private DatePicker edDate;
    @FXML
    private TextField edTime;
    @FXML
    private TextField edDuration;
    // для фильтра
    @FXML
    private TextField filterFrom;
    @FXML
    private TextField filterTo;
    @FXML
    private DatePicker filterSince;
    @FXML
    private DatePicker filterFor;
    @FXML
    private Label debugLabel;

    public MainFormController(){}

    void loadCallRecords(){
        callList.add(new CallRecord(1,"Гомер","Ленни",2,4,2020,13,01,5));
        callList.add(new CallRecord(2,"Барт","Милхауз",4,8,2018,15,15,12));
        callList.add(new CallRecord(3,"Мардж","Петти",12,11,2019,12,11,7));
        callList.add(new CallRecord(4,"Нельсон","Ральф",7,12,2016,14,21,4));
        callList.add(new CallRecord(5,"Лиза","Джо",23,9,2017,18,45,2));
        callList.add(new CallRecord(6,"Эдна","Сеймур",6,11,2017,18,34,9));
        callList.add(new CallRecord(7,"Мо","Гомер",1,1,2015,20,51,10));

    }
    @FXML
    private void initialize(){
        loadCallRecords();
        tvNum.setCellValueFactory(CallRecord -> CallRecord.getValue().prNum().asObject());
        tvFrom.setCellValueFactory(CallRecord -> CallRecord.getValue().prFrom());
        tvTo.setCellValueFactory(CallRecord -> CallRecord.getValue().prTo());
        tvDate.setCellValueFactory(CallRecord -> CallRecord.getValue().prDate().asString());
        tvTime.setCellValueFactory(CallRecord -> CallRecord.getValue().prTime().asString());
        tvDuration.setCellValueFactory(CallRecord -> CallRecord.getValue().prDuration().asObject());
        tableView.setItems(filteredCallList);

    }

    @FXML
    private void deleteAction(){
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) filteredCallList.getSource().remove(index);
    }

    @FXML
    private void addAction(){
        CallRecord newRecord = new CallRecord(0,"от кого","кому",1,1,1,1,1,1);
        newRecord.setNum(Integer.parseInt(edNum.getText()));
        newRecord.setFrom(edFrom.getText());
        newRecord.setTo(edTo.getText());
        newRecord.setDate(edDate.getValue());
        newRecord.setTime(LocalTime.parse(edTime.getText(), DateTimeFormatter.ofPattern("HH:mm")));
        newRecord.setDuration(Integer.parseInt(edDuration.getText()));
        callList.add(newRecord);
    }

    private boolean predic(CallRecord record) { // trim() - убирает пробелы с поля
        boolean a = (filterFrom.getText().trim().isEmpty()) || (record.getFrom().equals(filterFrom.getText()));
        boolean b = (filterTo.getText().trim().isEmpty()) || (record.getTo().equals(filterTo.getText().trim()));

        boolean c = (filterSince.getValue() == null) || (filterSince.getValue().isBefore(record.getDate()));
        boolean d = (filterFor.getValue() == null)||(filterFor.getValue().isAfter(record.getDate()));
        return a && b && c && d;
    }

    @FXML
    private void applyFilter(){
        filteredCallList.setPredicate(this::predic);
        tableView.setItems(filteredCallList);
    }

    @FXML
    private void clearFilter(){
        filteredCallList.setPredicate(p->true);
        filterFrom.setText("");
        filterTo.setText("");
        filterSince.setValue(null);
        filterFor.setValue(null);
    }


}
