package com.shang.gp


/**
 * Created with IntelliJ IDEA.
 * User: shangrz
 * Date: 13-3-6
 * Time: 下午9:52
 * To change this template use File | Settings | File Templates.
 */

def exampletree() {
    return new Node(Operator.IFW, [
            new Node(Operator.GTW, [new ParamNode(0), new ConstNode(3)]),
            new Node(Operator.ADDW, [new ParamNode(1), new ConstNode(5)]),
            new Node(Operator.SUBW, [new ParamNode(1), new ConstNode(2)]),
    ])
}


def makerandomtree(def pc, maxdepth = 4, fpr = 0.5, ppr = 0.6) {
    def random = new Random(System.currentTimeMillis())
    if (random.nextFloat() < fpr && maxdepth > 0) {
        def f = Operator.FLIST[random.nextInt(Operator.FLIST.size())]
        def children = (0..<f.childcount).collect {

            makerandomtree(pc, maxdepth - 1, fpr, ppr)
        }
        return new Node(f, children)

    } else if (random.nextFloat() < ppr) {

        return new ParamNode(new Random(System.currentTimeMillis()).nextInt(pc.toInteger()))
    } else
        return new ConstNode(new Random(System.currentTimeMillis()).nextInt(11))


}


def hiddenfunction2(def x, y) {
    return x ** 2 + 2 * y + 3 * x + 5
}

def hiddenfunction(def x, y) {
    return x+3
}


def buildhiddenset() {
    def random = new Random(System.currentTimeMillis())
    return (0..<200).collect() {
        x = random.nextInt(41)
        y = random.nextInt(41)
        return [x, y, hiddenfunction(x, y)]
    }

}


def scorefunction(def tree, s) {
    def dif = 0
    s.each { data ->
        v = tree.evaluate([data[0], data[1]])
        dif += (v - data[2]).abs()
    }
    return dif

}



def mutate(def t=null, pc=null, probchange = 0.1) {
    def random = new Random(System.currentTimeMillis())
    if (random.nextFloat() < probchange)
    {
        return makerandomtree(pc)}
    else {
//        def result = copy(t)
//        def result = t.clone()


        def result =   t
        def ss = new Expando()
        if (ss.hasProperty('children')) {
            result.children = t?.children?.collect {
                mutate(it, pc, probchange)
            }
        }
        return result

    }

}


def crossover(def t1, t2, probswap = 0.7, top = 1) {

    def random = new Random(System.currentTimeMillis())
    def rand = random.nextFloat()

    if (rand < probswap && rand != top) {
        return t2
//        return t2?.clone()        //deepcopy(t2)
    } else {
//        def result = t1?.clone()         //deepcopy(t1)
        def result = t1
        if (t1.hasProperty('children') && t2.hasProperty('children')) {
            result.children = t1.children?.collect {
                crossover(it, t2?.children[random.nextInt(t2?.children.size())], probswap, 0)
            }
        }
        return result
    }


}


def getrankfunction(def dataset) {
    return { def population ->
        def scores = population.collect { [scorefunction(it, dataset), it]}.sort()
//        scores=[(scorefunction(t,dataset),t) for t in population]
        return scores
    }
}


def evolve(def pc, def popsize, def rankfunction=null, def maxgen = 500,def mutationrate = 0.1,def breedingrate = 0.4,def  pexp = 0.7,def pnew = 0.05) {

    // Returns a random number, tending towards lower numbers. The lower pexp
    // is, more lower numbers you will get
    def random = new Random(System.currentTimeMillis())
    def selectindex = {
        return (Math.log(random.nextFloat()) / Math.log(pexp)).toInteger()
    }
    //Create a random initial population

    population = (0..<popsize).collect { makerandomtree(pc) }
    def scores
    for (i in (0..<maxgen)) {
        scores = rankfunction(population)
        if (scores[0][0] == 0) break
        def newpop = [scores[0][1], scores[1][1]]
        while (newpop.size() < popsize) {
            random.nextFloat() > pnew ? mutate(crossover(scores[selectindex()][1], scores[selectindex()][1],  breedingrate),
                    pc,  mutationrate) : makerandomtree(pc)
        }
        population = newpop
    }
    if (scores && scores[0]) {
        scores[0][1].display()
        return scores[0][1]
    } else {
        return null
    }
}



def gridgame(def p){
    def max = [3,3]
    def lastmove = [-1,-1]

}


def tournament(def pl) {
    // Count losses
    def losses = pl.collect { 0 }
    for (i in (0..<pl.size())) {
        for (j in (0..<pl.size())) {
            if (i == j) continue
            def winner = gridgame([pl[i], pl[j]])
            switch (winner) {
                case 0:
                    losses[j] += 2
                case 1:
                    losses[i] += 2
                case -1:
                    losses[i] += 1
                    losses[i] += 1

            }

        }

    }
    def z = [losses, pl].transpose().sort()
    return z
}

class HumanPlayer {
    Scanner sc = new Scanner(System.in);

    def evalute(board) {
        def me = board[0..1].toSet()
        def others = (2..<board.size() - 1).step(2).collect {
            board[it..it + 1].toSet()
        }

        (0..3).each { i ->
            (0..3).each { j ->
                if ([i, j].toSet().equals(me)) println 'O'
                else if ([i, j].toSet() in others) println 'X'
                else println '.'
                println ""
            }
        }

        println "Your last move was ${board[-1]}"
        println ' 0'
        println '2 3'
        println ' 1'
        println 'Enter move: '
        def move = sc.nextInt()
        return move
    }

}

def main(){
//    def et = exampletree()
//    println et.evaluate([2,3])
//
//    def r = makerandomtree(2)
//    r.evaluate([7,1])
////    r.display()
//    def hs = buildhiddenset()
//    println scorefunction(r,hs)
//    def m =mutate(r,2)
//    m.display()
    def rf = getrankfunction(buildhiddenset())
    def rs = evolve(2,500,rf,0.2,0.1,0.7,0.1)

}
main()

