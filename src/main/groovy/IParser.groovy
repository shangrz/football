import groovy.util.slurpersupport.GPathResult

/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-1-1
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
public interface IParser {
    Map parser(GPathResult html,Map r)
}