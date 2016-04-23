package shapely

trait Remover[A, L <: HList] {
  type Out <: HList

  def apply(xs: L): Out
}

private[shapely] trait RemoverLowPriorityImplicits {

  implicit def base[A, L <: HList]: Remover.Aux[A, A :: L, L] = new Remover[A, A :: L] {
    type Out = L

    def apply(xs: A :: L) = xs.tail
  }
}

object Remover extends RemoverLowPriorityImplicits {
  type Aux[A, L <: HList, Out0 <: HList] = Remover[A, L] { type Out = Out0 }

  implicit def corecurseRemove[A, L <: HList](implicit R: Remover[A, L]): Remover.Aux[A, A :: L, R.Out] = new Remover[A, A :: L] {
    type Out = R.Out

    def apply(xs: A :: L) = R(xs.tail)
  }

  implicit def corecurseRebuild[A, B, L <: HList](implicit R: Remover[A, L]): Remover.Aux[A, B :: L, B :: R.Out] = new Remover[A, B :: L] {
    type Out = B :: R.Out

    def apply(xs: B :: L) = xs.head :: R(xs.tail)
  }
}