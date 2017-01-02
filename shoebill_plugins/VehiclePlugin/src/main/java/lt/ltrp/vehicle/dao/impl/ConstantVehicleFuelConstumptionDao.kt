package lt.ltrp.vehicle.dao.impl

import lt.ltrp.vehicle.dao.VehicleFuelConsumptionDao

/**
 * Created by Bebras on 2016-12-29.
 *
 */
class ConstantVehicleFuelConstumptionDao: VehicleFuelConsumptionDao {


    override fun get(vehicleModel: Int): Float {
        return fuelConsumptions[vehicleModel] ?: 0f
    }

    override fun update(vehicleModel: Int, consumption: Float) {
        fuelConsumptions[vehicleModel] = consumption
    }

    override fun insert(vehicleModel: Int, consumption: Float) {
        fuelConsumptions[vehicleModel] = consumption
    }

    override fun remove(vehicleModel: Int) {
        fuelConsumptions.remove(vehicleModel)
    }

    companion object {
        val fuelConsumptions = mutableMapOf<Int, Float>()

        init {
            fuelConsumptions.put(400, 2f)
            fuelConsumptions.put(401, 2f)
            fuelConsumptions.put(402, 3f)
            fuelConsumptions.put(403, 5f)
            fuelConsumptions.put(404, 1f)
            fuelConsumptions.put(405, 2f)
            fuelConsumptions.put(406, 8f)
            fuelConsumptions.put(407, 4f)
            fuelConsumptions.put(408, 1f)
            fuelConsumptions.put(409, 3f)
            fuelConsumptions.put(410, 1f)
            fuelConsumptions.put(411, 3f)
            fuelConsumptions.put(412, 2f)
            fuelConsumptions.put(413, 1f)
            fuelConsumptions.put(414, 1f)
            fuelConsumptions.put(415, 3f)
            fuelConsumptions.put(416, 2f)
            fuelConsumptions.put(417, 5f)
            fuelConsumptions.put(418, 1f)
            fuelConsumptions.put(419, 2f)
            fuelConsumptions.put(420, 1f)
            fuelConsumptions.put(421, 1f)
            fuelConsumptions.put(422, 1f)
            fuelConsumptions.put(423, 2f)
            fuelConsumptions.put(424, 2f)
            fuelConsumptions.put(425, 8f)
            fuelConsumptions.put(426, 2f)
            fuelConsumptions.put(427, 3f)
            fuelConsumptions.put(428, 3f)
            fuelConsumptions.put(429, 3f)
            fuelConsumptions.put(430, 2f)
            fuelConsumptions.put(431, 3f)
            fuelConsumptions.put(432, 8f)
            fuelConsumptions.put(433, 5f)
            fuelConsumptions.put(434, 2f)
            fuelConsumptions.put(435, 0f)
            fuelConsumptions.put(436, 1f)
            fuelConsumptions.put(437, 3f)
            fuelConsumptions.put(438, 1f)
            fuelConsumptions.put(439, 2f)
            fuelConsumptions.put(440, 1f)
            fuelConsumptions.put(441, 0f)
            fuelConsumptions.put(442, 2f)
            fuelConsumptions.put(443, 5f)
            fuelConsumptions.put(444, 6f)
            fuelConsumptions.put(445, 1f)
            fuelConsumptions.put(446, 3f)
            fuelConsumptions.put(447, 1f)
            fuelConsumptions.put(448, 1f)
            fuelConsumptions.put(449, 0f)
            fuelConsumptions.put(450, 0f)
            fuelConsumptions.put(451, 3f)
            fuelConsumptions.put(452, 3f)
            fuelConsumptions.put(453, 1f)
            fuelConsumptions.put(454, 1f)
            fuelConsumptions.put(455, 3f)
            fuelConsumptions.put(456, 2f)
            fuelConsumptions.put(457, 1f)
            fuelConsumptions.put(458, 2f)
            fuelConsumptions.put(459, 2f)
            fuelConsumptions.put(460, 4f)
            fuelConsumptions.put(461, 1f)
            fuelConsumptions.put(462, 1f)
            fuelConsumptions.put(463, 1f)
            fuelConsumptions.put(464, 1f)
            fuelConsumptions.put(465, 1f)
            fuelConsumptions.put(466, 2f)
            fuelConsumptions.put(467, 2f)
            fuelConsumptions.put(468, 1f)
            fuelConsumptions.put(469, 4f)
            fuelConsumptions.put(470, 4f)
            fuelConsumptions.put(471, 1f)
            fuelConsumptions.put(472, 3f)
            fuelConsumptions.put(473, 1f)
            fuelConsumptions.put(474, 2f)
            fuelConsumptions.put(475, 2f)
            fuelConsumptions.put(476, 3f)
            fuelConsumptions.put(477, 3f)
            fuelConsumptions.put(478, 1f)
            fuelConsumptions.put(479, 1f)
            fuelConsumptions.put(480, 1f)
            fuelConsumptions.put(481, 0f)
            fuelConsumptions.put(482, 2f)
            fuelConsumptions.put(483, 2f)
            fuelConsumptions.put(484, 3f)
            fuelConsumptions.put(485, 1f)
            fuelConsumptions.put(486, 5f)
            fuelConsumptions.put(487, 5f)
            fuelConsumptions.put(488, 5f)
            fuelConsumptions.put(489, 3f)
            fuelConsumptions.put(490, 3f)
            fuelConsumptions.put(491, 1f)
            fuelConsumptions.put(492, 1f)
            fuelConsumptions.put(493, 3f)
            fuelConsumptions.put(494, 3f)
            fuelConsumptions.put(495, 3f)
            fuelConsumptions.put(496, 2f)
            fuelConsumptions.put(497, 4f)
            fuelConsumptions.put(498, 2f)
            fuelConsumptions.put(499, 1f)
            fuelConsumptions.put(500, 2f)
            fuelConsumptions.put(501, 1f)
            fuelConsumptions.put(502, 3f)
            fuelConsumptions.put(503, 3f)
            fuelConsumptions.put(504, 3f)
            fuelConsumptions.put(505, 2f)
            fuelConsumptions.put(506, 3f)
            fuelConsumptions.put(507, 2f)
            fuelConsumptions.put(508, 3f)
            fuelConsumptions.put(509, 0f)
            fuelConsumptions.put(510, 0f)
            fuelConsumptions.put(511, 6f)
            fuelConsumptions.put(512, 5f)
            fuelConsumptions.put(513, 5f)
            fuelConsumptions.put(514, 6f)
            fuelConsumptions.put(515, 7f)
            fuelConsumptions.put(516, 1f)
            fuelConsumptions.put(517, 1f)
            fuelConsumptions.put(518, 2f)
            fuelConsumptions.put(519, 9f)
            fuelConsumptions.put(520, 12f)
            fuelConsumptions.put(521, 1f)
            fuelConsumptions.put(522, 2f)
            fuelConsumptions.put(523, 1f)
            fuelConsumptions.put(524, 6f)
            fuelConsumptions.put(525, 2f)
            fuelConsumptions.put(526, 1f)
            fuelConsumptions.put(527, 1f)
            fuelConsumptions.put(528, 3f)
            fuelConsumptions.put(529, 1f)
            fuelConsumptions.put(530, 1f)
            fuelConsumptions.put(531, 2f)
            fuelConsumptions.put(532, 3f)
            fuelConsumptions.put(533, 2f)
            fuelConsumptions.put(534, 2f)
            fuelConsumptions.put(535, 2f)
            fuelConsumptions.put(536, 2f)
            fuelConsumptions.put(537, 0f)
            fuelConsumptions.put(538, 0f)
            fuelConsumptions.put(539, 1f)
            fuelConsumptions.put(540, 2f)
            fuelConsumptions.put(541, 3f)
            fuelConsumptions.put(542, 2f)
            fuelConsumptions.put(543, 1f)
            fuelConsumptions.put(544, 5f)
            fuelConsumptions.put(545, 2f)
            fuelConsumptions.put(546, 1f)
            fuelConsumptions.put(547, 1f)
            fuelConsumptions.put(548, 9f)
            fuelConsumptions.put(549, 2f)
            fuelConsumptions.put(550, 2f)
            fuelConsumptions.put(551, 2f)
            fuelConsumptions.put(552, 3f)
            fuelConsumptions.put(553, 15f)
            fuelConsumptions.put(554, 2f)
            fuelConsumptions.put(555, 2f)
            fuelConsumptions.put(556, 7f)
            fuelConsumptions.put(557, 7f)
            fuelConsumptions.put(558, 2f)
            fuelConsumptions.put(559, 2f)
            fuelConsumptions.put(560, 2f)
            fuelConsumptions.put(561, 2f)
            fuelConsumptions.put(562, 2f)
            fuelConsumptions.put(563, 7f)
            fuelConsumptions.put(564, 1f)
            fuelConsumptions.put(565, 2f)
            fuelConsumptions.put(566, 2f)
            fuelConsumptions.put(567, 2f)
            fuelConsumptions.put(568, 3f)
            fuelConsumptions.put(569, 0f)
            fuelConsumptions.put(570, 0f)
            fuelConsumptions.put(571, 1f)
            fuelConsumptions.put(572, 1f)
            fuelConsumptions.put(573, 5f)
            fuelConsumptions.put(574, 1f)
            fuelConsumptions.put(575, 2f)
            fuelConsumptions.put(576, 2f)
            fuelConsumptions.put(577, 20f)
            fuelConsumptions.put(578, 4f)
            fuelConsumptions.put(579, 2f)
            fuelConsumptions.put(580, 1f)
            fuelConsumptions.put(581, 1f)
            fuelConsumptions.put(582, 2f)
            fuelConsumptions.put(583, 1f)
            fuelConsumptions.put(584, 0f)
            fuelConsumptions.put(585, 2f)
            fuelConsumptions.put(586, 1f)
            fuelConsumptions.put(587, 2f)
            fuelConsumptions.put(588, 2f)
            fuelConsumptions.put(589, 2f)
            fuelConsumptions.put(590, 0f)
            fuelConsumptions.put(591, 0f)
            fuelConsumptions.put(592, 20f)
            fuelConsumptions.put(593, 6f)
            fuelConsumptions.put(594, 1f)
            fuelConsumptions.put(595, 4f)
            fuelConsumptions.put(596, 1f)
            fuelConsumptions.put(597, 2f)
            fuelConsumptions.put(598, 1f)
            fuelConsumptions.put(599, 2f)
            fuelConsumptions.put(600, 2f)
            fuelConsumptions.put(601, 4f)
            fuelConsumptions.put(602, 2f)
            fuelConsumptions.put(603, 3f)
            fuelConsumptions.put(604, 3f)
            fuelConsumptions.put(605, 2f)
            fuelConsumptions.put(606, 0f)
            fuelConsumptions.put(607, 0f)
            fuelConsumptions.put(608, 0f)
            fuelConsumptions.put(609, 1f)
            fuelConsumptions.put(610, 0f)
            fuelConsumptions.put(611, 0f)
        }
    }
}