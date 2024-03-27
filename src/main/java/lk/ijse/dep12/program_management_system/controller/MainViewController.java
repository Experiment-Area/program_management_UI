package lk.ijse.dep12.program_management_system.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lk.ijse.dep12.program_management_system.Program;

import java.util.ArrayList;

public class MainViewController {
    public Button btnNewProgram;
    public Button btnSave;
    public TextField txtShortCode;
    public TextField txtName;
    public TextField txtID;
    public VBox vBox;
    public CheckBox chkSchoolLeavers;
    public CheckBox chkGraduates;
    public CheckBox chkUndergraduates;
    public CheckBox chkProfessionals;
    public VBox vBoxAudience;
    public ToggleGroup grpType;
    public RadioButton rdPart;
    public RadioButton rdFull;

    private ArrayList<Program> programsList = new ArrayList<>();

    private void enableUI(boolean enable) {
        vBox.setDisable(!enable);
    }

    private String generatedNewProgramID() {
        if (programsList.isEmpty()) return "P001";

        int newID = Integer.parseInt(programsList.get(programsList.size() - 1).getId()
                .replace("P", "")) + 1;
        return "P%03d".formatted(newID);
    }

    public void initialize() {
        for (Node node : vBox.getChildren()) {
            HBox hBox = (HBox) node;
            for (int i = 0; i < hBox.getChildren().size(); i++) {
                if (hBox.getChildren().get(i) instanceof Label lbl) {
                    lbl.setLabelFor(hBox.getChildren().get(i + 1));
                }
            }
        }
        enableUI(false);
    }

    public void btnNewProgramOnAction(ActionEvent actionEvent) {
        enableUI(true);
        txtName.requestFocus();
        txtID.setText(generatedNewProgramID());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().strip();
        String shortCode = txtShortCode.getText();

        ArrayList<String> audience = new ArrayList<>();
        if (chkProfessionals.isSelected()) {
            audience.add(chkProfessionals.getText());
        }
        if (chkUndergraduates.isSelected()) {
            audience.add(chkUndergraduates.getText());
        }
        if (chkSchoolLeavers.isSelected()) {
            audience.add(chkSchoolLeavers.getText());
        }
        if (chkGraduates.isSelected()) {
            audience.add(chkGraduates.getText());
        }

        boolean validate = true;
        for (Control control : new Control[]{txtName, txtShortCode, rdFull, rdPart, chkSchoolLeavers, chkGraduates, chkUndergraduates, chkProfessionals}) {
            control.getStyleClass().remove("error");
        }

        if (!(chkUndergraduates.isSelected() || chkGraduates.isSelected() || chkProfessionals.isSelected() || chkSchoolLeavers.isSelected())) {
            chkSchoolLeavers.getStyleClass().add("error");
            chkProfessionals.getStyleClass().add("error");
            chkUndergraduates.getStyleClass().add("error");
            chkGraduates.getStyleClass().add("error");
            chkSchoolLeavers.requestFocus();
            validate = false;
        }

        if (grpType.getSelectedToggle() == null) {
            rdFull.getStyleClass().add("error");
            rdPart.getStyleClass().add("error");
            rdFull.requestFocus();
            validate = false;
        }

        if (!shortCode.isEmpty() && (txtShortCode.getText().strip().length() < 2 || txtShortCode.getText().strip().length() > 5)) {
            txtShortCode.getStyleClass().add("error");
            txtShortCode.requestFocus();
            validate = false;
        }

        if (name.isEmpty()) {
            txtName.getStyleClass().add("error");
            txtName.requestFocus();
            validate = false;
        }

        if (!validate) return;

        Program program = new Program(txtID.getText(),
                name,
                shortCode,
                ((RadioButton) grpType.getSelectedToggle()).getText(),
                audience);
        programsList.add(program);

        for (TextField txt : new TextField[]{txtID, txtName, txtShortCode, txtShortCode}) {
            txt.clear();
        }
        chkGraduates.setSelected(false);
        chkUndergraduates.setSelected(false);
        chkProfessionals.setSelected(false);
        chkSchoolLeavers.setSelected(false);
        grpType.selectToggle(null);
        enableUI(false);
    }
}
