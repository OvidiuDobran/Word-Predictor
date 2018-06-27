package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class MenuPage extends MyComposite {

	Button fileButton;
	Button keyboardButton;
	Button statButton;

	public MenuPage(Composite parent) {
		super(parent);
	}

	@Override
	public void generateContent() {
		Label welcomeLabel = new Label(this, SWT.BOLD);
		welcomeLabel.setText("Welcome!");
		FontData fontData = welcomeLabel.getFont().getFontData()[0];
		Font font = new Font(getDisplay(), new FontData(fontData.getName(), 18, SWT.BOLD));
		welcomeLabel.setFont(font);

		FormData welcomeLData = new FormData();
		welcomeLData.top = new FormAttachment(0, 20);
		welcomeLData.left = new FormAttachment(0, 20);
		welcomeLabel.setLayoutData(welcomeLData);

		Label descriptionLabel1 = new Label(this, SWT.BOLD);
		descriptionLabel1.setText(
				"I am a Word-Predictor! You can choose whether to input the text from the keyboard, or from a file.");
		FormData descriptionLData1 = new FormData();
		descriptionLData1.top = new FormAttachment(welcomeLabel, 20);
		descriptionLData1.left = new FormAttachment(0, 20);
		descriptionLabel1.setLayoutData(descriptionLData1);

		Label descriptionLabel2 = new Label(this, SWT.BOLD);
		descriptionLabel2.setText("I can also perform other words-related operations.");
		FormData descriptionLData2 = new FormData();
		descriptionLData2.top = new FormAttachment(descriptionLabel1, 20);
		descriptionLData2.left = new FormAttachment(0, 20);
		descriptionLabel2.setLayoutData(descriptionLData2);

		keyboardButton = new Button(this, SWT.NONE);
		keyboardButton.setText("Enter text from keyboard");

		FormData keyBData = new FormData();
		keyBData.top = new FormAttachment(descriptionLabel2, 50);
		keyBData.left = new FormAttachment(0, getShell().getSize().x / 2 - 120);
		keyBData.right = new FormAttachment(100, -getShell().getSize().x / 2 + 100);
		keyboardButton.setLayoutData(keyBData);

		fileButton = new Button(this, SWT.NONE);
		fileButton.setText("Enter text from file");

		FormData fileBData = new FormData();
		fileBData.top = new FormAttachment(keyboardButton, 30);
		fileBData.left = new FormAttachment(0, getShell().getSize().x / 2 - 120);
		fileBData.right = new FormAttachment(100, -getShell().getSize().x / 2 + 100);
		fileButton.setLayoutData(fileBData);

		statButton = new Button(this, SWT.NONE);
		statButton.setText("Statistics");

		FormData statBData = new FormData();
		statBData.top = new FormAttachment(fileButton, 30);
		statBData.left = new FormAttachment(0, getShell().getSize().x / 2 - 120);
		statBData.right = new FormAttachment(100, -getShell().getSize().x / 2 + 100);
		statButton.setLayoutData(statBData);

	}

}
