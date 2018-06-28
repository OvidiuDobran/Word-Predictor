package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FilePage extends MyComposite implements Refreshable {

	private String filePath;
	Text path;

	public FilePage(Composite parent) {
		super(parent);
	}

	@Override
	public void generateContent() {
		Label fileLabel = new Label(this, SWT.BOLD);
		fileLabel.setText("Enter the path of a file");
		FontData fontData = fileLabel.getFont().getFontData()[0];
		Font font = new Font(getDisplay(), new FontData(fontData.getName(), 16, SWT.BOLD));
		fileLabel.setFont(font);
		FormData fileLData = new FormData();
		fileLData.top = new FormAttachment(0, 30);
		fileLData.left = new FormAttachment(0, 30);
		fileLabel.setLayoutData(fileLData);

		path = new Text(this, SWT.BORDER);
		FormData pathData = new FormData();
		pathData.top = new FormAttachment(fileLabel, 30);
		pathData.left = new FormAttachment(0, 30);
		pathData.width = 300;
		path.setLayoutData(pathData);
	}

	public String getFilePath() {
		return filePath;
	}

	@Override
	public void refresh() {
		path.setText("");
	}

}
