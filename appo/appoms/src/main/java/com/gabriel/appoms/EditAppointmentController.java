package com.gabriel.appoms;
import com.gabriel.appoms.model.Appointment;
import com.gabriel.appoms.service.AppointmentService;
import com.gabriel.appoms.model.Owner;
import com.gabriel.appoms.service.OwnerService;
import com.gabriel.appoms.model.Pet;
import com.gabriel.appoms.service.PetService;
import com.gabriel.appoms.model.Groomer;
import com.gabriel.appoms.service.GroomerService;
import com.gabriel.appoms.model.ServiceType;
import com.gabriel.appoms.service.ServiceTypeService;
import com.gabriel.appoms.model.ApptStatus;
import com.gabriel.appoms.service.ApptStatusService;
import com.gabriel.appoms.model.Appt;
import com.gabriel.appoms.service.ApptService;
import com.gabriel.appoms.model.Owner;
import com.gabriel.appoms.service.OwnerService;
import com.gabriel.appoms.model.Pet;
import com.gabriel.appoms.service.PetService;
import com.gabriel.appoms.model.Groomer;
import com.gabriel.appoms.service.GroomerService;
import com.gabriel.appoms.model.ServiceType;
import com.gabriel.appoms.service.ServiceTypeService;
import com.gabriel.appoms.model.ApptStatus;
import com.gabriel.appoms.service.ApptStatusService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.net.URL;
import java.util.ResourceBundle;
import lombok.Setter;

public class EditAppointmentController extends GenericAppointmentController {
	public ImageView imgAppointment;
	@Override
	public void init() {
		setFields("Edit");
		enableFields(true);
	}
	public void onSubmit(ActionEvent actionEvent) {
		try {
			Appointment appointment = toObject(true);
			Appointment newAppointment = AppointmentService.getService().update(appointment);
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Manage Appointment");
			stage.setScene(manageScene);
			stage.show();
		}
		catch (Exception e){
			showErrorDialog("Error encountered creating appointment", e.getMessage());
		}
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
