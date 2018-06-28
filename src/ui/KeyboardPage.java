package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class KeyboardPage extends MyComposite implements Refreshable {

	Text inputText;
	Combo suggestions;

	public KeyboardPage(Composite parent) {
		super(parent);
	}

	@Override
	public void generateContent() {
		Label inputLabel = new Label(this, SWT.BOLD);
		inputLabel.setText("Enter the text from keyboard");
		FontData fontData = inputLabel.getFont().getFontData()[0];
		Font font = new Font(getDisplay(), new FontData(fontData.getName(), 16, SWT.BOLD));
		inputLabel.setFont(font);
		FormData inputLData = new FormData();
		inputLData.top = new FormAttachment(0, 30);
		inputLData.left = new FormAttachment(0, 30);
		inputLabel.setLayoutData(inputLData);

		suggestions = new Combo(this, SWT.BORDER);
		FormData suggestionData = new FormData();
		suggestionData.top = new FormAttachment(inputLabel, 30);
		suggestionData.left = new FormAttachment(0, 30);
		suggestions.setLayoutData(suggestionData);
		
		suggestions.setItems(new String[] {"A","B","C"});

		inputText = new Text(this, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		FormData inputTData = new FormData();
		inputTData.top = new FormAttachment(suggestions, 30);
		inputTData.bottom = new FormAttachment(100, -30);
		inputTData.left = new FormAttachment(0, 30);
		inputTData.right = new FormAttachment(100, -30);
		inputText.setLayoutData(inputTData);
	}

	@Override
	public void refresh() {
		inputText.setText("");
	}

}
