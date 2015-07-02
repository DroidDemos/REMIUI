package com.xiaomi.b.a;

final class e {
    private final t nF;
    private final J nG;
    private final s nH;
    private final v nI;
    private final q nJ;
    private final u nK;
    private final r nL;
    private final D nM;
    private final p nN;
    private final C nO;
    private final B nP;
    private final boolean nQ;

    private e(t tVar, J j, s sVar, v vVar, q qVar, u uVar, r rVar, D d, p pVar, C c, B b, boolean z) {
        this.nF = tVar;
        this.nG = j;
        this.nH = sVar;
        this.nI = vVar;
        this.nJ = qVar;
        this.nK = uVar;
        this.nL = rVar;
        this.nM = d;
        this.nN = pVar;
        this.nO = c;
        this.nP = b;
        this.nQ = z;
    }

    static e a(z zVar, z zVar2) {
        C aq = C.aq(zVar2.a(K.px));
        boolean z = aq != null && ((String) aq.cw()).equals(zVar.a(K.pL));
        return new e(t.aj(a(zVar2, K.pO)), J.as(a(zVar2, K.pU)), s.ai(zVar2.a(K.pT)), v.am(zVar2.a(K.pI)), q.ag(zVar2.a(K.pD)), u.al(zVar2.a(K.pK)), r.ah(zVar2.a(K.pC)), D.ar(zVar2.a(K.pv)), p.af(zVar2.a(K.pF)), aq, B.ao(zVar2.a(K.py)), z);
    }

    private static String a(z zVar, j jVar) {
        String a = zVar.a(jVar);
        if (a != null) {
            return a;
        }
        throw new aa("Connection Manager session creation response did not include required '" + jVar.b() + "' attribute");
    }

    t ca() {
        return this.nF;
    }

    J cb() {
        return this.nG;
    }

    s cc() {
        return this.nH;
    }

    v cd() {
        return this.nI;
    }

    p ce() {
        return this.nN;
    }

    boolean f() {
        return this.nQ;
    }
}
