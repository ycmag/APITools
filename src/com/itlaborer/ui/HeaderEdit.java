package com.itlaborer.ui;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.itlaborer.utils.ApiUtils;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class HeaderEdit extends Dialog {

	private static Logger logger = Logger.getLogger(HeaderEdit.class.getName());
	protected Shell headerEditShell;
	private Table formTable;
	private Text[][] form;
	private Label[] label;
	private int parsSum;
	private boolean saveFlag;
	private String windowname;
	private Map<String, String> pars;
	private LinkedHashMap<String, String> headerNew;

	public HeaderEdit(Shell parent, String windowname,int style) {
		super(parent, style);
		this.windowname=windowname;
		logger.info("进入"+windowname+"参数工具");
	}

	public LinkedHashMap<String, String> open(LinkedHashMap<String, String> header) {
		parsSum = 24;
		saveFlag = false;
		this.pars = header;
		createContents();
		headerEditShell.open();
		headerEditShell.layout();
		Display display = getParent().getDisplay();
		while (!headerEditShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if (saveFlag) {
			return headerNew;
		} else {
			return header;
		}
	}

	private void createContents() {
		headerEditShell = new Shell(getParent(), getStyle());
		headerEditShell.setImage(SWTResourceManager.getImage(HeaderEdit.class, "/com/itlaborer/res/icon.ico"));
		headerEditShell.setSize(680, 420);
		headerEditShell.setText(windowname+"参数工具");
		ApiUtils.SetCenterinParent(getParent(), headerEditShell);

		formTable = new Table(headerEditShell, SWT.BORDER | SWT.HIDE_SELECTION);
		formTable.setBounds(6, 8, 662, 346);
		formTable.setHeaderVisible(true);
		formTable.setLinesVisible(true);
		formTable.setItemCount(parsSum);

		// 表列
		TableColumn tblclmnNewColumn = new TableColumn(formTable, SWT.BORDER);
		tblclmnNewColumn.setWidth(38);
		tblclmnNewColumn.setResizable(false);
		tblclmnNewColumn.setText("编号");

		TableColumn nameColumn = new TableColumn(formTable, SWT.BORDER);
		nameColumn.setWidth(
				(formTable.getBounds().width - tblclmnNewColumn.getWidth() - formTable.getVerticalBar().getSize().x - 4)
						/ 2);
		nameColumn.setText("参数名");
		nameColumn.setResizable(false);

		TableColumn valueColumn_1 = new TableColumn(formTable, SWT.BORDER);
		valueColumn_1.setWidth(
				(formTable.getBounds().width - tblclmnNewColumn.getWidth() - formTable.getVerticalBar().getSize().x - 4)
						/ 2);
		valueColumn_1.setText("参数值");
		valueColumn_1.setResizable(false);

		Button btnNewButton = new Button(headerEditShell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveFlag = true;
				headerNew = new LinkedHashMap<>();
				for (int i = 0; i < form.length; i++) {
					if (notEmpty(form[i][0].getText(), form[i][1].getText())) {
						headerNew.put(form[i][0].getText(), form[i][1].getText());
					}
				}
				headerEditShell.dispose();
			}
		});
		btnNewButton.setBounds(5, 360, 342, 27);
		btnNewButton.setText("保存并退出");

		Button button = new Button(headerEditShell, SWT.NONE);
		button.setText("放弃并退出");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveFlag = false;
				headerEditShell.dispose();
			}
		});
		button.setBounds(353, 360, 317, 27);

		form = new Text[parsSum][2];
		label = new Label[parsSum];
		TableItem[] items = formTable.getItems();
		for (int i = 0; i < parsSum; i++) {
			// 第一列
			TableEditor editor0 = new TableEditor(formTable);
			label[i] = new Label(formTable, SWT.NONE | SWT.CENTER);
			label[i].setBackground(new Color(Display.getCurrent(), 255, 255, 255));
			label[i].setText(new DecimalFormat("000").format(i + 1));
			editor0.grabHorizontal = true;
			editor0.setEditor(label[i], items[i], 0);
			// 第二列
			TableEditor editor1 = new TableEditor(formTable);
			form[i][0] = new Text(formTable,SWT.NONE);
			form[i][0].setText(items[i].getText(1));
			editor1.grabHorizontal = true;
			editor1.setEditor(form[i][0], items[i], 1);
			// 第三列
			TableEditor editor2 = new TableEditor(formTable);
			form[i][1] = new Text(formTable, SWT.NONE);
			form[i][1].setText(items[i].getText(2));
			editor2.grabHorizontal = true;
			editor2.setEditor(form[i][1], items[i], 2);
			// 设置焦点变色
			int b = i;
			form[i][0].addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					label[b].setBackground(new Color(Display.getCurrent(), 255, 255, 255));
					form[b][0].setBackground(new Color(Display.getCurrent(), 255, 255, 255));
					form[b][1].setBackground(new Color(Display.getCurrent(), 255, 255, 255));
				}
				@Override
				public void focusGained(FocusEvent e) {
					label[b].setBackground(new Color(Display.getCurrent(), 227, 247, 255));
					form[b][0].setBackground(new Color(Display.getCurrent(), 227, 247, 255));
					form[b][1].setBackground(new Color(Display.getCurrent(), 227, 247, 255));
				}
			});
			form[i][1].addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					label[b].setBackground(new Color(Display.getCurrent(), 255, 255, 255));
					form[b][0].setBackground(new Color(Display.getCurrent(), 255, 255, 255));
					form[b][1].setBackground(new Color(Display.getCurrent(), 255, 255, 255));
				}

				@Override
				public void focusGained(FocusEvent e) {
					label[b].setBackground(new Color(Display.getCurrent(), 227, 247, 255));
					form[b][0].setBackground(new Color(Display.getCurrent(), 227, 247, 255));
					form[b][1].setBackground(new Color(Display.getCurrent(), 227, 247, 255));
				}
			});
		}
		initTable();
	}

	// 初始化
	private void initTable() {
		// 参数设定
		if (null == pars) {
			return;
		}
		int i = 0;
		Iterator<Entry<String, String>> queryIterator = pars.entrySet().iterator();
		for (; queryIterator.hasNext();) {
			if (i >= parsSum) {
				break;
			}
			Entry<String, String> nameVal = queryIterator.next();
			String name = nameVal.getKey();
			String value = nameVal.getValue();
			if (notEmpty(name, value)) {
				form[i][0].setText(name);
				form[i][1].setText(value);
				i++;
			}
		}
	}

	// 判断空
	public static boolean notEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !((null == value) || ("".equals(value)) ? true : false);
			}
		}
		return result;
	}
}