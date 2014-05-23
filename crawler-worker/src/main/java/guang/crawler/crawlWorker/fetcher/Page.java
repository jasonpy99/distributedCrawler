package guang.crawler.crawlWorker.fetcher;

import guang.crawler.core.WebURL;
import guang.crawler.crawlWorker.parser.ParseData;

import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * This class contains the data for a fetched and parsed page. 该类用意表示下载的页面
 * 
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */
public class Page
{
	protected static final Logger	logger	= Logger.getLogger(Page.class
	                                               .getName());
	/**
	 * The URL of this page.
	 */
	protected WebURL	          url;
	
	/**
	 * The content of this page in binary format.
	 */
	protected byte[]	          contentData;
	
	/**
	 * The ContentType of this page. For example: "text/html; charset=UTF-8"
	 */
	protected String	          contentType;
	
	/**
	 * The encoding of the content. For example: "gzip"
	 */
	protected String	          contentEncoding;
	
	/**
	 * The charset of the content. For example: "UTF-8"
	 */
	protected String	          contentCharset;
	
	/**
	 * Headers which were present in the response of the fetch request
	 */
	protected Header[]	          fetchResponseHeaders;
	
	/**
	 * The parsed data populated by parsers
	 */
	protected ParseData	          parseData;
	
	public Page(WebURL url)
	{
		this.url = url;
	}
	
	/**
	 * Returns the charset of the content. For example: "UTF-8"
	 */
	public String getContentCharset()
	{
		return this.contentCharset;
	}
	
	/**
	 * Returns the content of this page in binary format.
	 */
	public byte[] getContentData()
	{
		return this.contentData;
	}
	
	/**
	 * Returns the encoding of the content. For example: "gzip"
	 */
	public String getContentEncoding()
	{
		return this.contentEncoding;
	}
	
	/**
	 * Returns the ContentType of this page. For example:
	 * "text/html; charset=UTF-8"
	 */
	public String getContentType()
	{
		return this.contentType;
	}
	
	/**
	 * Returns headers which were present in the response of the fetch request
	 */
	public Header[] getFetchResponseHeaders()
	{
		return this.fetchResponseHeaders;
	}
	
	/**
	 * Returns the parsed data generated for this page by parsers
	 */
	public ParseData getParseData()
	{
		return this.parseData;
	}
	
	public WebURL getWebURL()
	{
		return this.url;
	}
	
	/**
	 * Loads the content of this page from a fetched HttpEntity.
	 */
	public void load(HttpEntity entity) throws Exception
	{
		
		this.contentType = null;
		Header type = entity.getContentType();
		if (type != null)
		{
			this.contentType = type.getValue();
		}
		
		this.contentEncoding = null;
		Header encoding = entity.getContentEncoding();
		if (encoding != null)
		{
			this.contentEncoding = encoding.getValue();
		}
		
		Charset charset = ContentType.getOrDefault(entity).getCharset();
		if (charset != null)
		{
			this.contentCharset = charset.displayName();
		}
		
		this.contentData = EntityUtils.toByteArray(entity);
		
	}
	
	public void setContentCharset(String contentCharset)
	{
		this.contentCharset = contentCharset;
	}
	
	public void setContentData(byte[] contentData)
	{
		this.contentData = contentData;
	}
	
	public void setContentEncoding(String contentEncoding)
	{
		this.contentEncoding = contentEncoding;
	}
	
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}
	
	public void setFetchResponseHeaders(Header[] headers)
	{
		this.fetchResponseHeaders = headers;
	}
	
	public void setParseData(ParseData parseData)
	{
		this.parseData = parseData;
	}
	
	public void setWebURL(WebURL url)
	{
		this.url = url;
	}
	
}