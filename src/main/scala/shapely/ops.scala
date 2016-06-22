package shapely

trait Remover[A, L <: HList] {
  type Out <: HList

  def apply(xs: L): Out
}

private[shapely] trait RemoverLowPriorityImplicits {
  type Aux[A, L <: HList, O <: HList] = Remover[A, L] { type Out = O }

  implicit def nilRemove[A]: Remover[A, HNil] = new Remover[A, HNil] {
    type Out = HNil

    def apply(xs: HNil) = HNil
  }

  implicit def corecurseRebuild[A, B, L <: HList](implicit R: Remover[A, L]): Remover.Aux[A, B :: L, B :: R.Out] = new Remover[A, B :: L] {
    type Out = B :: R.Out

    def apply(xs: B :: L) = xs.head :: R(xs.tail)
  }
}

object Remover extends RemoverLowPriorityImplicits {

  implicit def corecurseRemove[A, L <: HList](implicit R: Remover[A, L]): Remover.Aux[A, A :: L, R.Out] = new Remover[A, A :: L] {
    type Out = R.Out

    def apply(xs: A :: L) = R(xs.tail)
  }
}

trait Mapper[L <: HList, P <: Poly] {
  type Out <: HList

  def apply(xs: L): Out
}

object Mapper {
  type Aux[L <: HList, P <: Poly, Out0 <: HList] = Mapper[L, P] { type Out = Out0 }

  implicit def base[P <: Poly]: Mapper.Aux[HNil, P, HNil] = new Mapper[HNil, P] {
    type Out = HNil

    def apply(xs: HNil) = xs
  }

  implicit def corecurse[A, B, L <: HList, P <: Poly](implicit C: P#Case[A, B], M: Mapper[L, P]): Mapper.Aux[A :: L, P, B :: M.Out] = new Mapper[A :: L, P] {
    type Out = B :: M.Out

    def apply(xs: A :: L) = C(xs.head) :: M(xs.tail)
  }
}

trait Nther[L <: HList, N <: Nat] {
  type Out

  def apply(xs: L): Out
}

object Nther {
  type Aux[L <: HList, N <: Nat, Out0] = Nther[L, N] { type Out = Out0 }

  implicit def base[A, L <: HList]: Nther.Aux[A :: L, Zero, A] = new Nther[A :: L, Zero] {
    type Out = A

    def apply(xs: A :: L) = xs.head
  }

  implicit def corecurse[A, L <: HList, N <: Nat](implicit N: Nther[L, N]): Nther.Aux[A :: L, Succ[N], N.Out] = new Nther[A :: L, Succ[N]] {
    type Out = N.Out

    def apply(xs: A :: L) = N(xs.tail)
  }
}

trait ToInt[N <: Nat] {
  val value: Int
}

object ToInt {

  implicit def base: ToInt[Zero] = new ToInt[Zero] {
    val value = 0
  }

  implicit def succ[N <: Nat](implicit N: ToInt[N]): ToInt[Succ[N]] = new ToInt[Succ[N]] {
    val value = 1 + N.value
  }
}