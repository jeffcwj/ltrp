package lt.ltrp.house.rent.event

import lt.ltrp.house.`object`.House
import lt.ltrp.`object`.LtrpPlayer
import lt.ltrp.`object`.PlayerData
import lt.ltrp.event.property.house.HouseEvent

/**
 * Created by Bebras on 2016-10-06.
 */
class HouseUnrentEvent(house: House, val player: PlayerData): HouseEvent(house) {
}