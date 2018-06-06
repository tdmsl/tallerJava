/*
 * Created on 09-jul-2004
 */
package es.tdmsl.app.dao.scrollableTableModel;

/**
 * Exception used in <code>ScrollableTableModel</code>.
 * @author Francesc Roses
 */
public class ScrollableTableModelException extends RuntimeException {
	/**
	 * 
	 */
	public ScrollableTableModelException() {
		super();
	}

	/**
	 * @param message
	 */
	public ScrollableTableModelException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ScrollableTableModelException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ScrollableTableModelException(String message, Throwable cause) {
		super(message, cause);
	}
}

