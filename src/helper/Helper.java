package helper;

import javax.swing.JOptionPane;

public class Helper {
	
	
	public static void showMsg(String msg)  {

	
	switch (msg) {
	case "error":
		msg="Islem tamamlanamadi hata olustu";
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.ERROR_MESSAGE);
		break;
		
	case "success":
		msg="Islem basari ile gerceklesti";
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
		break;

	default:
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);

		break;
	}
		
		
		
	}
		
	
	
public static boolean confirm(String msg) {

    switch (msg) {
    case "sure":
        msg = "Bu islemi gerceklestirmek istiyor musunuz?";
        break;

    default:
        break;
    }

    int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat", JOptionPane.YES_NO_CANCEL_OPTION);
    if (res == 0) {
        return true;
    } else {
        return false;
    }

}
}
