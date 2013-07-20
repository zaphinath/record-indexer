/**
 * 
 */
package shared.communication;

import java.net.URL;

/**
 * @author zaphinath
 *
 */
public class GetSampleImage_Result {
	
	private URL imageUrl;
	
	/**
	 * @return the imageUrl
	 */
	public URL getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(URL imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		if (imageUrl != null) {
			return imageUrl.toString() + "\n";
		} else {
			return "FAILED\n";
		}
	}

}
