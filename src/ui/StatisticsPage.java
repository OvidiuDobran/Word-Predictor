package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class StatisticsPage extends MyComposite {

	public StatisticsPage(Composite parent) {
		super(parent);
	}

	@Override
	public void generateContent() {

		Label statLabel = new Label(this, SWT.BOLD);
		statLabel.setText("Statistics");
		FontData fontData = statLabel.getFont().getFontData()[0];
		Font font = new Font(getDisplay(), new FontData(fontData.getName(), 16, SWT.BOLD));
		statLabel.setFont(font);
		FormData statLData = new FormData();
		statLData.top = new FormAttachment(0, 30);
		statLData.left = new FormAttachment(0, 30);
		statLabel.setLayoutData(statLData);

		Table table = new Table(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setHeaderVisible(true);
		String[] titles = { "Word", "Frequency" };

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
		}

		for (int loopIndex = 0; loopIndex < 24; loopIndex++) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText("Item " + loopIndex);
			item.setText(0, "Item " + loopIndex);
			item.setText(1, "Yes");
		}

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			table.getColumn(loopIndex).pack();
		}

		table.setBounds(25, 25, 220, 200);

		FormData tableData = new FormData();
		tableData.top = new FormAttachment(statLabel, 30);
		tableData.bottom = new FormAttachment(100, -30);
		tableData.right = new FormAttachment(100, -30);
		tableData.left = new FormAttachment(0, 30);
		table.setLayoutData(tableData);

	}

}
