package ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import application.Application;
import models.Word;

public class UIHandler {
	private static volatile UIHandler instance;
	private Display display;
	private Shell shell;
	MenuPage menuPage;
	KeyboardPage keyboardPage;
	FilePage filePage;
	StatisticsPage statisticsPage;

	private Composite contentPanel;
	private StackLayout layout;
	private Button exitButton;
	private Button menuButton;
	private Button processButton;
	private boolean isExit;
	private Application application;

	private UIHandler(Application application) {
		this.application = application;
		display = Display.getDefault();
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.MIN);
		shell.setSize(900, 600);

	}

	public static UIHandler getInstance(Application application) {
		if (instance == null) {
			synchronized (UIHandler.class) {
				if (instance == null) {
					return new UIHandler(application);
				}
			}
		}
		return instance;
	}

	public void run() {
		generateContent();
		addBehaviours();
		changePageTo(menuPage);

		shell.open();

		while (!shell.isDisposed() && !isExit) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void generateContent() {
		FormLayout formLayout = new FormLayout();
		shell.setLayout(formLayout);
		shell.setText("Word-Predictor");

		exitButton = new Button(shell, SWT.NONE);
		exitButton.setText("Exit");
		FormData exitBData = new FormData();
		exitBData.bottom = new FormAttachment(100, -20);
		exitBData.right = new FormAttachment(100, -20);
		exitBData.width = 80;
		exitButton.setLayoutData(exitBData);

		menuButton = new Button(shell, SWT.NONE);
		menuButton.setText("Menu");
		FormData menuBData = new FormData();
		menuBData.bottom = new FormAttachment(100, -20);
		menuBData.right = new FormAttachment(exitButton, -20);
		menuBData.width = 80;
		menuButton.setLayoutData(menuBData);

		processButton = new Button(shell, SWT.NONE);
		processButton.setText("Process");
		FormData processBData = new FormData();
		processBData.bottom = new FormAttachment(100, -20);
		processBData.right = new FormAttachment(menuButton, -20);
		processBData.width = 80;
		processButton.setLayoutData(processBData);

		contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBounds(100, 10, 190, 90);
		layout = new StackLayout();
		contentPanel.setLayout(layout);

		FormData contPanData = new FormData();
		contPanData.bottom = new FormAttachment(exitButton, -30);
		contPanData.top = new FormAttachment(0, 0);
		contPanData.left = new FormAttachment(0, 0);
		contPanData.right = new FormAttachment(100, 0);
		contentPanel.setLayoutData(contPanData);

		menuPage = new MenuPage(contentPanel);
		keyboardPage = new KeyboardPage(contentPanel);
		filePage = new FilePage(contentPanel);
		statisticsPage = new StatisticsPage(contentPanel);

	}

	private void addBehaviours() {

		this.menuButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				changePageTo(menuPage);
			}
		});

		this.exitButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				isExit = true;
			}
		});

		this.processButton.addListener(SWT.Selection, new Listener() {

			MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
			{
				messageBox.setText("Done");
				messageBox.setMessage("Your input was proccessed and saved. Thnaks for improving my knowledge!");
			}

			@Override
			public void handleEvent(Event arg0) {
				if (layout.topControl == filePage) {
					String filePath = filePage.path.getText();
					try {
						application.processTextFromFile(filePath);

						messageBox.open();
						changePageTo(menuPage);
					} catch (IOException e) {
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.ERROR);
						messageBox.setText("Invalid path");
						messageBox.setMessage(
								"I couldn't process the file you have submitted. Please make sure the path is valid!");
						messageBox.open();
					}

				} else {
					if (layout.topControl == keyboardPage) {
						application.processTextFromKeyoboard(keyboardPage.inputText.getText());

						messageBox.open();
						changePageTo(menuPage);

					}
				}
			}
		});

		menuPage.keyboardButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				changePageTo(keyboardPage);
			}
		});

		menuPage.fileButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				changePageTo(filePage);

			}
		});

		menuPage.statButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				changePageTo(statisticsPage);
			}
		});

		keyboardPage.inputText.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub

			}

		});

		keyboardPage.inputText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				String currentText = keyboardPage.inputText.getText();
				System.err.println(currentText);
				if (!currentText.endsWith(" ")) {
					System.out.println("INSIDE WORD");
					String currentWord;
					if (currentText.contains(" ")) {
						currentWord = currentText.substring(currentText.lastIndexOf(" "));
					} else {
						currentWord = currentText;
					}
					List<String> wordSuggestions = application.getWordStartingWith(currentWord);
					String[] arrSuggestions = new String[wordSuggestions.size()];
					for (int i = 0; i < wordSuggestions.size(); i++) {
						arrSuggestions[i] = wordSuggestions.get(i);
					}
					System.out.println(Arrays.asList(wordSuggestions));
					keyboardPage.suggestions.setItems(arrSuggestions);
					// keyboardPage.suggestions.setItems(new String[] {"A11","B11","C11"});

					System.out.println("INSIDE WORD " + currentWord);
				} else {
					int startIndex = currentText.substring(0, currentText.length() - 1).lastIndexOf(" ");
					if (startIndex < 0) {
						startIndex = 0;
					}
					int endIndex = currentText.lastIndexOf(" ");
					String lastWord = currentText.substring(startIndex, endIndex);
					List<Word> nextWords = application.getNextWordsOf(lastWord.trim());
					String[] arrSuggestions = new String[nextWords.size()];
					for (int i = 0; i < nextWords.size(); i++) {
						arrSuggestions[i] = nextWords.get(i).getValue();
					}
					keyboardPage.suggestions.setItems(arrSuggestions);

					System.out.println("END OF WORD: " + lastWord);
				}
			}
		});

	}

	public void changePageTo(MyComposite composite) {
		layout.topControl = composite;
		contentPanel.layout();
		if (composite instanceof MenuPage) {
			this.menuButton.setEnabled(false);
			this.menuButton.setVisible(false);

			this.processButton.setEnabled(false);
			this.processButton.setVisible(false);
		} else if (composite instanceof StatisticsPage) {
			this.menuButton.setEnabled(true);
			this.menuButton.setVisible(true);

			this.processButton.setEnabled(false);
			this.processButton.setVisible(false);
		} else {
			this.menuButton.setEnabled(true);
			this.menuButton.setVisible(true);

			this.processButton.setEnabled(true);
			this.processButton.setVisible(true);
		}
		if (composite instanceof Refreshable) {
			((Refreshable) composite).refresh();
		}
	}

}
