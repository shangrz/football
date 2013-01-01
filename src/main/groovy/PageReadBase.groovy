import groovy.json.JsonBuilder
import org.ccil.cowan.tagsoup.AutoDetector
import org.ccil.cowan.tagsoup.Parser

/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-1-1
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
abstract class PageReadBase implements IParser{
    URL url
    String encode = 'GBK'

    public test(){
        def json =new JsonBuilder(getResult())
        println json.toPrettyString()
    }

    def getResult(){
        def htmlString = this.getUrl().getText(this.getEncode())
        return this.parser(htmlString)
    }


    final autoDetectorPropertyName = 'http://www.ccil.org/~cowan/tagsoup/properties/auto-detector'
    def asHTML(content,encoding="GB2312"){
        Parser parser = new org.ccil.cowan.tagsoup.Parser()
        parser.setProperty(autoDetectorPropertyName, [autoDetectingReader: {inputStream ->
            new InputStreamReader(inputStream, encoding)
        }
        ] as AutoDetector)

        new XmlSlurper(parser).parseText(content)
    }

    def parser(String htmlString){
        def r = [:]
//        println this.getEncode()
        def html = asHTML(htmlString,this.getEncode())
//        println html
        r = parser(html,r)
        return r
    }
}

