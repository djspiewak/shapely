package shapely

trait HList {
  type Append[L <: HList] <: HList

  def ++[L <: HList](xs: L): Append[L]
}

final case class HCons[H, T <: HList](head: H, tail: T) extends HList {
  override type Append[L <: HList] = H :: T#Append[L]

  override def ++[L <: HList](xs: L): Append[L] =
    HCons(head, tail ++ xs)
}

case object HNil extends HList {
  override type Append[L <: HList] = L

  override def ++[L <: HList](xs: L): L =
    xs
}