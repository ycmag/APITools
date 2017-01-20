package com.itlaborer.apitools.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.itlaborer.apitools.res.Resource;
import com.itlaborer.apitools.utils.ApiUtils;
import org.eclipse.swt.widgets.Label;

public class CreateCollectionDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private boolean saveFlag = false;
	////////////////////////////////
	private String name;
	private String description;
	private String path;
	///////////////////////////////
	private String serverAddress;
	private Text nameText;
	private Text descriptionText;
	private Text serverText;
	private Text pathText;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public CreateCollectionDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object[] open(String serverAddress) {
		this.serverAddress = serverAddress;
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return (new Object[] { saveFlag, name, description, path });
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setImage(SWTResourceManager.getImage(CreateCollectionDialog.class, Resource.IMAGE_ICON));
		shell.setText("创建一个新的接口");
		shell.setSize(480, 220);
		ApiUtils.SetCenterinParent(getParent(), shell);

		Button buttonYes = new Button(shell, SWT.NONE);
		buttonYes.setBounds(293, 155, 80, 27);
		buttonYes.setText("保存");
		buttonYes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveFlag = true;
				name = nameText.getText();
				description = descriptionText.getText();
				path = pathText.getText();
				shell.dispose();
			}
		});

		Button buttonNo = new Button(shell, SWT.NONE);
		buttonNo.setBounds(384, 155, 80, 27);
		buttonNo.setText("放弃");

		nameText = new Text(shell, SWT.BORDER);
		nameText.setBounds(10, 35, 454, 23);

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label.setBounds(10, 18, 61, 17);
		label.setText("接口名");

		descriptionText = new Text(shell, SWT.BORDER);
		descriptionText.setBounds(10, 81, 454, 23);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_1.setBounds(10, 64, 61, 17);
		label_1.setText("备注");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("路径");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_2.setBounds(10, 109, 61, 17);

		serverText = new Text(shell, SWT.BORDER | SWT.RIGHT);
		serverText.setBounds(10, 126, 281, 23);
		serverText.setText(serverAddress + "");

		pathText = new Text(shell, SWT.BORDER);
		pathText.setBounds(293, 126, 171, 23);
		buttonNo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveFlag = false;
				shell.dispose();
			}
		});
	}
}
