package com.shang.gp

/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-3-6
 * Time: 下午9:48
 * To change this template use File | Settings | File Templates.
 */
class ConstNode {
    def v

    ConstNode(def v) {
        this.v = v
    }

    def evaluate(def inp) {
        return this.v
    }

    def display(def indent = 0) {
        println ' ' * indent + v
    }
}
