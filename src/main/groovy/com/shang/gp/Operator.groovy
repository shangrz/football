package com.shang.gp

/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-3-6
 * Time: 下午10:05
 * To change this template use File | Settings | File Templates.
 */
class Operator {

    static def ADDW = new Fwrapper({ l ->
        return l[0] + l[1]
    }, 2, 'add')
    static def SUBW = new Fwrapper({ l -> return l[0] - l[1] }, 2, 'subtract')
    static def MULW = new Fwrapper({ l -> return l[0]  * l[1] }, 2, 'multiply')
    static def IFW = new Fwrapper({ l -> return l[0] > 0 ? l[1] : l[2]}, 3, 'if')
    static def GTW = new Fwrapper({ l -> return l[0] > l[1] ? 1 : 0 }, 2, 'isgreater')

    static def FLIST = [ADDW, MULW, IFW, GTW, SUBW]

    public static void main(String[] args) {
        def s = ADDW
    }
}



