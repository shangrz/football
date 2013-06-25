package com.shang.gp

/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-2-14
 * Time: 上午12:47
 * To change this template use File | Settings | File Templates.
 */
class Fwrapper {
    def function
    def childcount
    def name
    Fwrapper(def function ,childcount, name){
        this.function = function
        this.childcount = childcount
        this.name = name
    }
}
