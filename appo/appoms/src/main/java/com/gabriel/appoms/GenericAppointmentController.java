package com.gabriel.appoms;
import com.gabriel.appoms.model.Appointment;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.Window;
import lombok.Setter;
import javafx.util.StringConverter;
import java.net.URL;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class GenericAppointmentController implements Initializable{
	@Setter
	CreateAppointmentController createAppointmentController;

	@Setter
	DeleteAppointmentController deleteAppointmentController ;

	@Setter
	EditAppointmentController editAppointmentController;

	@Setter
	ManageAppointmentController manageAppointmentController;

	@Setter
	Stage stage;

	@Setter
	Scene splashScene;

	@Setter
	Scene manageScene;

	@Setter
	public ListView<Appointment> lvAppointments;

	@Setter
	public static Appointment selectedItem;
	public TextField txtId;
	public ComboBox<Owner> cmbOwner;
	public TextField txtOwnerFirstName;
	public TextField txtOwnerLastName;
	public TextField txtOwnerEmail;
	public TextField txtOwnerPhoneNumber;
	public TextField txtOwnerAddress;
	public ComboBox<Pet> cmbPet;
	public TextField txtPetName;
	public TextField txtPetBreed;
	public TextField txtPetAge;
	public TextField txtPetMedicalNotes;
	public ComboBox<Groomer> cmbGroomer;
	public TextField txtGroomerName;
	public ComboBox<ServiceType> cmbServiceType;
	public TextField txtServiceTypeName;
	public TextField txtServiceTypeDescription;
	public TextField txtServicePrice;
	public ComboBox<ApptStatus> cmbApptStatus;
	public TextField txtApptStatusName;
	public ComboBox<Appt> cmbAppt;
	public DatePicker dtApptDate;
	public TextField txtApptNotes;
	public ComboBox<Owner> cmbOwner;
	public ComboBox<Pet> cmbPet;
	public ComboBox<Groomer> cmbGroomer;
	public ComboBox<ServiceType> cmbServiceType;
	public ComboBox<ApptStatus> cmbApptStatus;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Owner[] owners =  (Owner[]) OwnerService.getService().getAll();
		cmbOwner.getItems().addAll(owners);
		StringConverter<Owner> ownerConverter = new StringConverter<Owner>() {
			@Override
			public String toString(Owner owner) {
			if(owner==null)
				return "";
			else
				return owner.toString();
			}
			@Override
			public Owner fromString(String s) {
				if(!s.isEmpty()){
					for (Owner owner : owners) {
						if (s.equals(owner.getName())){
							return owner;
						}
					}
				}
				return null;
			}
		};
		cmbOwner.setConverter(ownerConverter);
		Pet[] pets =  (Pet[]) PetService.getService().getAll();
		cmbPet.getItems().addAll(pets);
		StringConverter<Pet> petConverter = new StringConverter<Pet>() {
			@Override
			public String toString(Pet pet) {
			if(pet==null)
				return "";
			else
				return pet.toString();
			}
			@Override
			public Pet fromString(String s) {
				if(!s.isEmpty()){
					for (Pet pet : pets) {
						if (s.equals(pet.getName())){
							return pet;
						}
					}
				}
				return null;
			}
		};
		cmbPet.setConverter(petConverter);
		Groomer[] groomers =  (Groomer[]) GroomerService.getService().getAll();
		cmbGroomer.getItems().addAll(groomers);
		StringConverter<Groomer> groomerConverter = new StringConverter<Groomer>() {
			@Override
			public String toString(Groomer groomer) {
			if(groomer==null)
				return "";
			else
				return groomer.toString();
			}
			@Override
			public Groomer fromString(String s) {
				if(!s.isEmpty()){
					for (Groomer groomer : groomers) {
						if (s.equals(groomer.getName())){
							return groomer;
						}
					}
				}
				return null;
			}
		};
		cmbGroomer.setConverter(groomerConverter);
		ServiceType[] serviceTypes =  (ServiceType[]) ServiceTypeService.getService().getAll();
		cmbServiceType.getItems().addAll(serviceTypes);
		StringConverter<ServiceType> serviceTypeConverter = new StringConverter<ServiceType>() {
			@Override
			public String toString(ServiceType serviceType) {
			if(serviceType==null)
				return "";
			else
				return serviceType.toString();
			}
			@Override
			public ServiceType fromString(String s) {
				if(!s.isEmpty()){
					for (ServiceType serviceType : serviceTypes) {
						if (s.equals(serviceType.getName())){
							return serviceType;
						}
					}
				}
				return null;
			}
		};
		cmbServiceType.setConverter(serviceTypeConverter);
		ApptStatus[] apptStatuss =  (ApptStatus[]) ApptStatusService.getService().getAll();
		cmbApptStatus.getItems().addAll(apptStatuss);
		StringConverter<ApptStatus> apptStatusConverter = new StringConverter<ApptStatus>() {
			@Override
			public String toString(ApptStatus apptStatus) {
			if(apptStatus==null)
				return "";
			else
				return apptStatus.toString();
			}
			@Override
			public ApptStatus fromString(String s) {
				if(!s.isEmpty()){
					for (ApptStatus apptStatus : apptStatuss) {
						if (s.equals(apptStatus.getName())){
							return apptStatus;
						}
					}
				}
				return null;
			}
		};
		cmbApptStatus.setConverter(apptStatusConverter);
		Appt[] appts =  (Appt[]) ApptService.getService().getAll();
		cmbAppt.getItems().addAll(appts);
		StringConverter<Appt> apptConverter = new StringConverter<Appt>() {
			@Override
			public String toString(Appt appt) {
			if(appt==null)
				return "";
			else
				return appt.toString();
			}
			@Override
			public Appt fromString(String s) {
				if(!s.isEmpty()){
					for (Appt appt : appts) {
						if (s.equals(appt.getName())){
							return appt;
						}
					}
				}
				return null;
			}
		};
		cmbAppt.setConverter(apptConverter);
		Owner[] owners =  (Owner[]) OwnerService.getService().getAll();
		cmbOwner.getItems().addAll(owners);
		StringConverter<Owner> ownerConverter = new StringConverter<Owner>() {
			@Override
			public String toString(Owner owner) {
			if(owner==null)
				return "";
			else
				return owner.toString();
			}
			@Override
			public Owner fromString(String s) {
				if(!s.isEmpty()){
					for (Owner owner : owners) {
						if (s.equals(owner.getName())){
							return owner;
						}
					}
				}
				return null;
			}
		};
		cmbOwner.setConverter(ownerConverter);
		Pet[] pets =  (Pet[]) PetService.getService().getAll();
		cmbPet.getItems().addAll(pets);
		StringConverter<Pet> petConverter = new StringConverter<Pet>() {
			@Override
			public String toString(Pet pet) {
			if(pet==null)
				return "";
			else
				return pet.toString();
			}
			@Override
			public Pet fromString(String s) {
				if(!s.isEmpty()){
					for (Pet pet : pets) {
						if (s.equals(pet.getName())){
							return pet;
						}
					}
				}
				return null;
			}
		};
		cmbPet.setConverter(petConverter);
		Groomer[] groomers =  (Groomer[]) GroomerService.getService().getAll();
		cmbGroomer.getItems().addAll(groomers);
		StringConverter<Groomer> groomerConverter = new StringConverter<Groomer>() {
			@Override
			public String toString(Groomer groomer) {
			if(groomer==null)
				return "";
			else
				return groomer.toString();
			}
			@Override
			public Groomer fromString(String s) {
				if(!s.isEmpty()){
					for (Groomer groomer : groomers) {
						if (s.equals(groomer.getName())){
							return groomer;
						}
					}
				}
				return null;
			}
		};
		cmbGroomer.setConverter(groomerConverter);
		ServiceType[] serviceTypes =  (ServiceType[]) ServiceTypeService.getService().getAll();
		cmbServiceType.getItems().addAll(serviceTypes);
		StringConverter<ServiceType> serviceTypeConverter = new StringConverter<ServiceType>() {
			@Override
			public String toString(ServiceType serviceType) {
			if(serviceType==null)
				return "";
			else
				return serviceType.toString();
			}
			@Override
			public ServiceType fromString(String s) {
				if(!s.isEmpty()){
					for (ServiceType serviceType : serviceTypes) {
						if (s.equals(serviceType.getName())){
							return serviceType;
						}
					}
				}
				return null;
			}
		};
		cmbServiceType.setConverter(serviceTypeConverter);
		ApptStatus[] apptStatuss =  (ApptStatus[]) ApptStatusService.getService().getAll();
		cmbApptStatus.getItems().addAll(apptStatuss);
		StringConverter<ApptStatus> apptStatusConverter = new StringConverter<ApptStatus>() {
			@Override
			public String toString(ApptStatus apptStatus) {
			if(apptStatus==null)
				return "";
			else
				return apptStatus.toString();
			}
			@Override
			public ApptStatus fromString(String s) {
				if(!s.isEmpty()){
					for (ApptStatus apptStatus : apptStatuss) {
						if (s.equals(apptStatus.getName())){
							return apptStatus;
						}
					}
				}
				return null;
			}
		};
		cmbApptStatus.setConverter(apptStatusConverter);
		init();
	}
	protected void init(){
		System.out.println("Invoked from Generic Controller");
	}
	protected Appointment toObject(boolean isEdit){
		Appointment appointment= new Appointment();
		try {
			if(isEdit) {
				appointment.setId(Integer.parseInt(txtId.getText()));
			}
			Owner owner = cmbOwner.getSelectionModel().getSelectedItem();
			appointment.setOwnerId(owner.getId());
			appointment.setOwnerName(owner.getName());
			appointment.setOwnerEmail(txtOwnerEmail.getText());
			appointment.setOwnerPhoneNumber(txtOwnerPhoneNumber.getText());
			appointment.setOwnerAddress(txtOwnerAddress.getText());
			Pet pet = cmbPet.getSelectionModel().getSelectedItem();
			appointment.setPetId(pet.getId());
			appointment.setPetName(pet.getName());
			appointment.setPetBreed(txtPetBreed.getText());
			appointment.setPetAge(Integer.parseInt(txtPetAge.getText()));
			appointment.setPetMedicalNotes(txtPetMedicalNotes.getText());
			Groomer groomer = cmbGroomer.getSelectionModel().getSelectedItem();
			appointment.setGroomerId(groomer.getId());
			appointment.setGroomerName(groomer.getName());
			ServiceType serviceType = cmbServiceType.getSelectionModel().getSelectedItem();
			appointment.setServiceTypeId(serviceType.getId());
			appointment.setServiceTypeName(serviceType.getName());
			appointment.setServiceTypeDescription(txtServiceTypeDescription.getText());
			appointment.setServicePrice(Double.parseDouble(txtServicePrice.getText()));
			ApptStatus apptStatus = cmbApptStatus.getSelectionModel().getSelectedItem();
			appointment.setApptStatusId(apptStatus.getId());
			appointment.setApptStatusName(apptStatus.getName());
			Appt appt = cmbAppt.getSelectionModel().getSelectedItem();
			appointment.setApptId(appt.getId());
			appointment.setApptName(appt.getName());
			appointment.setApptDate(toDate(dtApptDate.getValue()));
			appointment.setApptNotes(txtApptNotes.getText());
			Owner owner = cmbOwner.getSelectionModel().getSelectedItem();
			appointment.setOwnerId(owner.getId());
			appointment.setOwnerName(owner.getName());
			Pet pet = cmbPet.getSelectionModel().getSelectedItem();
			appointment.setPetId(pet.getId());
			appointment.setPetName(pet.getName());
			Groomer groomer = cmbGroomer.getSelectionModel().getSelectedItem();
			appointment.setGroomerId(groomer.getId());
			appointment.setGroomerName(groomer.getName());
			ServiceType serviceType = cmbServiceType.getSelectionModel().getSelectedItem();
			appointment.setServiceTypeId(serviceType.getId());
			appointment.setServiceTypeName(serviceType.getName());
			ApptStatus apptStatus = cmbApptStatus.getSelectionModel().getSelectedItem();
			appointment.setApptStatusId(apptStatus.getId());
			appointment.setApptStatusName(apptStatus.getName());
		}catch (Exception e){
			showErrorDialog("Error" ,e.getMessage());
		}
		return appointment;
	}
	protected void setFields(String action){
		String formattedDate;
		Appointment appointment = GenericAppointmentController.selectedItem;
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
		txtId.setText(Integer.toString(appointment.getId()));
		Owner owner = OwnerService.getService().get(appointment.getOwnerId());
		cmbOwner.getSelectionModel().select(owner);
		if(action.equals("Create") || action.equals("Edit")){
			cmbOwner.setVisible(true);
			txtOwnerName.setVisible(false);
			cmbOwner.getSelectionModel().select(owner);
		}
		else{
			cmbOwner.setVisible(false);
			txtOwnerName.setVisible(true);
			txtOwnerName.setText(owner.getName());
		}
		txtOwnerFirstName.setText(appointment.getOwnerFirstName());
		txtOwnerLastName.setText(appointment.getOwnerLastName());
		txtOwnerEmail.setText(appointment.getOwnerEmail());
		txtOwnerPhoneNumber.setText(appointment.getOwnerPhoneNumber());
		txtOwnerAddress.setText(appointment.getOwnerAddress());
		Pet pet = PetService.getService().get(appointment.getPetId());
		cmbPet.getSelectionModel().select(pet);
		if(action.equals("Create") || action.equals("Edit")){
			cmbPet.setVisible(true);
			txtPetName.setVisible(false);
			cmbPet.getSelectionModel().select(pet);
		}
		else{
			cmbPet.setVisible(false);
			txtPetName.setVisible(true);
			txtPetName.setText(pet.getName());
		}
		txtPetName.setText(appointment.getPetName());
		txtPetBreed.setText(appointment.getPetBreed());
		txtPetAge.setText(Integer.toString(appointment.getPetAge()));
		txtPetMedicalNotes.setText(appointment.getPetMedicalNotes());
		Groomer groomer = GroomerService.getService().get(appointment.getGroomerId());
		cmbGroomer.getSelectionModel().select(groomer);
		if(action.equals("Create") || action.equals("Edit")){
			cmbGroomer.setVisible(true);
			txtGroomerName.setVisible(false);
			cmbGroomer.getSelectionModel().select(groomer);
		}
		else{
			cmbGroomer.setVisible(false);
			txtGroomerName.setVisible(true);
			txtGroomerName.setText(groomer.getName());
		}
		txtGroomerName.setText(appointment.getGroomerName());
		ServiceType serviceType = ServiceTypeService.getService().get(appointment.getServiceTypeId());
		cmbServiceType.getSelectionModel().select(serviceType);
		if(action.equals("Create") || action.equals("Edit")){
			cmbServiceType.setVisible(true);
			txtServiceTypeName.setVisible(false);
			cmbServiceType.getSelectionModel().select(serviceType);
		}
		else{
			cmbServiceType.setVisible(false);
			txtServiceTypeName.setVisible(true);
			txtServiceTypeName.setText(serviceType.getName());
		}
		txtServiceTypeName.setText(appointment.getServiceTypeName());
		txtServiceTypeDescription.setText(appointment.getServiceTypeDescription());
		txtServicePrice.setText(Double.toString(appointment.getServicePrice()));
		ApptStatus apptStatus = ApptStatusService.getService().get(appointment.getApptStatusId());
		cmbApptStatus.getSelectionModel().select(apptStatus);
		if(action.equals("Create") || action.equals("Edit")){
			cmbApptStatus.setVisible(true);
			txtApptStatusName.setVisible(false);
			cmbApptStatus.getSelectionModel().select(apptStatus);
		}
		else{
			cmbApptStatus.setVisible(false);
			txtApptStatusName.setVisible(true);
			txtApptStatusName.setText(apptStatus.getName());
		}
		txtApptStatusName.setText(appointment.getApptStatusName());
		Appt appt = ApptService.getService().get(appointment.getApptId());
		cmbAppt.getSelectionModel().select(appt);
		if(action.equals("Create") || action.equals("Edit")){
			cmbAppt.setVisible(true);
			txtApptName.setVisible(false);
			cmbAppt.getSelectionModel().select(appt);
		}
		else{
			cmbAppt.setVisible(false);
			txtApptName.setVisible(true);
			txtApptName.setText(appt.getName());
		}
		dtApptDate.setValue(toLocalDate(appointment.getApptDate()));
		txtApptNotes.setText(appointment.getApptNotes());
		Owner owner = OwnerService.getService().get(appointment.getOwnerId());
		cmbOwner.getSelectionModel().select(owner);
		if(action.equals("Create") || action.equals("Edit")){
			cmbOwner.setVisible(true);
			txtOwnerName.setVisible(false);
			cmbOwner.getSelectionModel().select(owner);
		}
		else{
			cmbOwner.setVisible(false);
			txtOwnerName.setVisible(true);
			txtOwnerName.setText(owner.getName());
		}
		Pet pet = PetService.getService().get(appointment.getPetId());
		cmbPet.getSelectionModel().select(pet);
		if(action.equals("Create") || action.equals("Edit")){
			cmbPet.setVisible(true);
			txtPetName.setVisible(false);
			cmbPet.getSelectionModel().select(pet);
		}
		else{
			cmbPet.setVisible(false);
			txtPetName.setVisible(true);
			txtPetName.setText(pet.getName());
		}
		Groomer groomer = GroomerService.getService().get(appointment.getGroomerId());
		cmbGroomer.getSelectionModel().select(groomer);
		if(action.equals("Create") || action.equals("Edit")){
			cmbGroomer.setVisible(true);
			txtGroomerName.setVisible(false);
			cmbGroomer.getSelectionModel().select(groomer);
		}
		else{
			cmbGroomer.setVisible(false);
			txtGroomerName.setVisible(true);
			txtGroomerName.setText(groomer.getName());
		}
		ServiceType serviceType = ServiceTypeService.getService().get(appointment.getServiceTypeId());
		cmbServiceType.getSelectionModel().select(serviceType);
		if(action.equals("Create") || action.equals("Edit")){
			cmbServiceType.setVisible(true);
			txtServiceTypeName.setVisible(false);
			cmbServiceType.getSelectionModel().select(serviceType);
		}
		else{
			cmbServiceType.setVisible(false);
			txtServiceTypeName.setVisible(true);
			txtServiceTypeName.setText(serviceType.getName());
		}
		ApptStatus apptStatus = ApptStatusService.getService().get(appointment.getApptStatusId());
		cmbApptStatus.getSelectionModel().select(apptStatus);
		if(action.equals("Create") || action.equals("Edit")){
			cmbApptStatus.setVisible(true);
			txtApptStatusName.setVisible(false);
			cmbApptStatus.getSelectionModel().select(apptStatus);
		}
		else{
			cmbApptStatus.setVisible(false);
			txtApptStatusName.setVisible(true);
			txtApptStatusName.setText(apptStatus.getName());
		}
	}

	protected void clearFields(String action){
		txtId.setText("");
		cmbOwner.getSelectionModel().clearSelection();
		txtOwnerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbOwner.setVisible(true);
			txtOwnerName.setVisible(false);
		}
		else{
			cmbOwner.setVisible(false);
			txtOwnerName.setVisible(true);
		}
		//txtOwnerFirstName.setText("");
		//txtOwnerLastName.setText("");
		//txtOwnerEmail.setText("");
		//txtOwnerPhoneNumber.setText("");
		//txtOwnerAddress.setText("");
		cmbPet.getSelectionModel().clearSelection();
		txtPetName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbPet.setVisible(true);
			txtPetName.setVisible(false);
		}
		else{
			cmbPet.setVisible(false);
			txtPetName.setVisible(true);
		}
		//txtPetName.setText("");
		//txtPetBreed.setText("");
		//txtPetAge.setText("");
		//txtPetMedicalNotes.setText("");
		cmbGroomer.getSelectionModel().clearSelection();
		txtGroomerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbGroomer.setVisible(true);
			txtGroomerName.setVisible(false);
		}
		else{
			cmbGroomer.setVisible(false);
			txtGroomerName.setVisible(true);
		}
		//txtGroomerName.setText("");
		cmbServiceType.getSelectionModel().clearSelection();
		txtServiceTypeName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbServiceType.setVisible(true);
			txtServiceTypeName.setVisible(false);
		}
		else{
			cmbServiceType.setVisible(false);
			txtServiceTypeName.setVisible(true);
		}
		//txtServiceTypeName.setText("");
		//txtServiceTypeDescription.setText("");
		//txtServicePrice.setText("");
		cmbApptStatus.getSelectionModel().clearSelection();
		txtApptStatusName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbApptStatus.setVisible(true);
			txtApptStatusName.setVisible(false);
		}
		else{
			cmbApptStatus.setVisible(false);
			txtApptStatusName.setVisible(true);
		}
		//txtApptStatusName.setText("");
		cmbAppt.getSelectionModel().clearSelection();
		txtApptName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbAppt.setVisible(true);
			txtApptName.setVisible(false);
		}
		else{
			cmbAppt.setVisible(false);
			txtApptName.setVisible(true);
		}
		//dtApptDate.setText("");
		//txtApptNotes.setText("");
		cmbOwner.getSelectionModel().clearSelection();
		txtOwnerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbOwner.setVisible(true);
			txtOwnerName.setVisible(false);
		}
		else{
			cmbOwner.setVisible(false);
			txtOwnerName.setVisible(true);
		}
		cmbPet.getSelectionModel().clearSelection();
		txtPetName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbPet.setVisible(true);
			txtPetName.setVisible(false);
		}
		else{
			cmbPet.setVisible(false);
			txtPetName.setVisible(true);
		}
		cmbGroomer.getSelectionModel().clearSelection();
		txtGroomerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbGroomer.setVisible(true);
			txtGroomerName.setVisible(false);
		}
		else{
			cmbGroomer.setVisible(false);
			txtGroomerName.setVisible(true);
		}
		cmbServiceType.getSelectionModel().clearSelection();
		txtServiceTypeName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbServiceType.setVisible(true);
			txtServiceTypeName.setVisible(false);
		}
		else{
			cmbServiceType.setVisible(false);
			txtServiceTypeName.setVisible(true);
		}
		cmbApptStatus.getSelectionModel().clearSelection();
		txtApptStatusName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbApptStatus.setVisible(true);
			txtApptStatusName.setVisible(false);
		}
		else{
			cmbApptStatus.setVisible(false);
			txtApptStatusName.setVisible(true);
		}
	}

	protected void enableFields(boolean enable){
		cmbOwner.editableProperty().set(enable);
		txtOwnerName.editableProperty().set(enable);
		txtOwnerFirstName.editableProperty().set(enable);
		txtOwnerLastName.editableProperty().set(enable);
		txtOwnerEmail.editableProperty().set(enable);
		txtOwnerPhoneNumber.editableProperty().set(enable);
		txtOwnerAddress.editableProperty().set(enable);
		cmbPet.editableProperty().set(enable);
		txtPetName.editableProperty().set(enable);
		txtPetName.editableProperty().set(enable);
		txtPetBreed.editableProperty().set(enable);
		txtPetAge.editableProperty().set(enable);
		txtPetMedicalNotes.editableProperty().set(enable);
		cmbGroomer.editableProperty().set(enable);
		txtGroomerName.editableProperty().set(enable);
		txtGroomerName.editableProperty().set(enable);
		cmbServiceType.editableProperty().set(enable);
		txtServiceTypeName.editableProperty().set(enable);
		txtServiceTypeName.editableProperty().set(enable);
		txtServiceTypeDescription.editableProperty().set(enable);
		txtServicePrice.editableProperty().set(enable);
		cmbApptStatus.editableProperty().set(enable);
		txtApptStatusName.editableProperty().set(enable);
		txtApptStatusName.editableProperty().set(enable);
		cmbAppt.editableProperty().set(enable);
		txtApptName.editableProperty().set(enable);
		dtApptDate.editableProperty().set(enable);
		txtApptNotes.editableProperty().set(enable);
		cmbOwner.editableProperty().set(enable);
		txtOwnerName.editableProperty().set(enable);
		cmbPet.editableProperty().set(enable);
		txtPetName.editableProperty().set(enable);
		cmbGroomer.editableProperty().set(enable);
		txtGroomerName.editableProperty().set(enable);
		cmbServiceType.editableProperty().set(enable);
		txtServiceTypeName.editableProperty().set(enable);
		cmbApptStatus.editableProperty().set(enable);
		txtApptStatusName.editableProperty().set(enable);
	}

	public int getId(){
		return Integer.parseInt(txtId.getText());
	}

	protected void showErrorDialog(String message, String expandedMessage){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(message);
		alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(expandedMessage)));
		alert.showAndWait();
	}
	public void onBack(ActionEvent actionEvent) {
		Node node = ((Node) (actionEvent.getSource()));
		Window window = node.getScene().getWindow();
		window.hide();
		stage.setScene(manageScene);
		stage.show();
	}
	public void onClose(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit and loose changes? " , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}
	LocalDate toLocalDate(Date date){
		Instant instant = date.toInstant();
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = instant.atZone( z );
		return zdt.toLocalDate();
	}
	protected Date toDate(LocalDate ld){
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = ld.atStartOfDay(z);
		Instant instant  = zdt.toInstant();
		return Date.from(instant);
	}
}

