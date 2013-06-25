package com.shang.gp
/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-2-14
 * Time: 上午12:48
 * To change this template use File | Settings | File Templates.
 */
class ParamNode {
    def idx

    def ParamNode(def idx) {
        this.idx = idx
    }

    def evaluate(def inp) {
        return inp[idx]
    }

    def display(def indent = 0) {
        println ' ' * indent + 'p'+idx
    }

}


