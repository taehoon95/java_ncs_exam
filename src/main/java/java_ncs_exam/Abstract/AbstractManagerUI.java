package java_ncs_exam.Abstract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import java_ncs_exam.exception.EmptyTfException;
import java_ncs_exam.exception.InvaildCheckException;

@SuppressWarnings("serial")
public abstract class AbstractManagerUI<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnClear;
	
	protected AbstractContentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;
	protected JMenuItem empListByTitleItem;
	private JMenuItem updateItem;
	private JMenuItem deleteItem;
	
	public AbstractManagerUI() {
		setService(); //service 연결
		
		initialize();
		
		tableLoadData(); // component load Data
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = createContentPanel();
		contentPane.add(pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);
		
		pList = createTablePanel();
		
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}


	public JMenuItem getUpdateItem() {
		return updateItem;
	}

	public JMenuItem getDeleteItem() {
		return deleteItem;
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	


	
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}
				
				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}
				
			}else {
				if (e.getSource() == btnClear) {
					actionPerformedBtnClear(e);
				}
				
				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					}else {
						actionPerformedBtnUpdate(e);
					}
				}
				
			}
		}catch ( EmptyTfException | InvaildCheckException e1  ) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),"오류",JOptionPane.ERROR_MESSAGE);
			pContent.clearTf();
			pContent.setEdit();
		}catch(IndexOutOfBoundsException e1) {
			JOptionPane.showMessageDialog(null, "해당 직책을 선택하세요.","선택 오류",JOptionPane.WARNING_MESSAGE);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
////////////////////////////////////////////////////////
	
	protected abstract void setService();
	
	protected abstract void tableLoadData();

	protected abstract AbstractContentPanel<T> createContentPanel();
	
	protected abstract AbstractCustomTablePanel<T> createTablePanel();
	
	protected abstract void actionPerformedMenuUpdate();
	
	protected abstract void actionPerformedMenuDelete();
	
	protected abstract void actionPerformedBtnUpdate(ActionEvent e);

	protected abstract void actionPerformedBtnAdd(ActionEvent e);
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		
		pContent.clearTf();
		pContent.setEdit();
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
