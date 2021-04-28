package java_ncs_exam.content;

import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java_ncs_exam.Abstract.AbstractContentPanel;
import java_ncs_exam.dto.Title;
import java_ncs_exam.exception.EmptyTfException;
import java_ncs_exam.exception.InvaildCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends AbstractContentPanel<Title> {

	private JTextField tfTitleNo;
	private JTextField tfTitleName;
	
	
	public JTextField getTfTitleNo() {
		return tfTitleNo;
	}


	public void setTfTitleNo(JTextField tfTitleNo) {
		this.tfTitleNo = tfTitleNo;
	}


	public TitlePanel() {
		initialize();
	}
	
	public void initialize() {
		setBorder(new TitledBorder(null, "직책", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTitleNo = new JLabel("직책번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleNo);
		
		tfTitleNo = new JTextField();
		add(tfTitleNo);
		tfTitleNo.setColumns(10);
		
		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleName);
		
		tfTitleName = new JTextField();
		tfTitleName.setColumns(10);
		add(tfTitleName);
	}


	@Override
	public void setItem(Title item) {
		tfTitleNo.setText(item.gettNo() + "");
		tfTitleName.setText(item.gettName());
	}


	@Override
	public Title getItem() {
		validCheck();
		typeCheck();
		
		int tNo = Integer.parseInt(tfTitleNo.getText().trim());
		if(tNo >= 1000) {
			throw new EmptyTfException(); 
			
		}
		
		String tName = tfTitleName.getText().trim();
		return new Title(tNo, tName);
	}


	@Override
	public void validCheck() {
		if(tfTitleNo.getText().contentEquals("") || tfTitleName.getText().equals("")) {
			throw new InvaildCheckException();
		}
	}
	
	@Override
	public void clearTf() {
		tfTitleNo.setText("");
		tfTitleName.setText("");
	}

	public void setEdit() {
		tfTitleNo.setEditable(true);
	}
	
	public void typeCheck() {
	      if (!Pattern.matches("^[0-9]*$", tfTitleNo.getText())
	            || !Pattern.matches("^[가-힣]*$", tfTitleName.getText())) {
	         throw new EmptyTfException();

	      }
	   }
}
