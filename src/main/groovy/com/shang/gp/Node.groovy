package com.shang.gp

/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-2-14
 * Time: 上午12:47
 * To change this template use File | Settings | File Templates.
 */
class Node {
    def function
    def name
    def children

    Node(def fw, children) {
        this.function = fw.function
        this.name = fw.name
        this.children = children
    }

    def evaluate(def inp) {
        def results = this.children.collect { it.evaluate(inp) }
        return this.function(results)
    }

    def display(def indent = 0) {
        println ' ' * indent + this.name
        children.each {
            it.display(indent + 1)
        }
    }
}
