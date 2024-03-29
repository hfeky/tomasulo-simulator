package com.husseinelfeky.tomasulosimulator.model.simulation

import com.husseinelfeky.tomasulosimulator.model.operation.BaseOperation
import com.husseinelfeky.tomasulosimulator.model.operation.Operation
import com.husseinelfeky.tomasulosimulator.model.simulation.base.SimulationItem
import com.husseinelfeky.tomasulosimulator.model.simulation.general.Tag
import com.husseinelfeky.tomasulosimulator.model.simulation.general.ValueReference

data class ReservationStation(
    override val tag: Tag,
    var operation: Operation? = null,
    var vj: ValueReference? = null,
    var vk: ValueReference? = null,
    var qj: Tag? = null,
    var qk: Tag? = null,
    override var isBusy: Boolean = false,
    override var instructionNumber: Int? = null,
    override var remainingCycles: Int? = null,
    override var showValues: Boolean = false
) : SimulationItem(tag, isBusy, instructionNumber, remainingCycles, showValues) {

    override fun canExecute(): Boolean {
        return isBusy && remainingCycles != 0 && vj != null && vk != null
    }

    override fun clear() {
        tag.assignedRegister = null
        operation = null
        vj = null
        vk = null
        qj = null
        qk = null
        isBusy = false
        instructionNumber = null
        remainingCycles = null
    }

    companion object {
        private const val STATIONS_ADD = 3
        private const val STATIONS_MUL = 2

        fun getAddStations(): List<ReservationStation> {
            return (1..STATIONS_ADD).toList().map {
                ReservationStation(Tag(BaseOperation.A, it))
            }
        }

        fun getMulStations(): List<ReservationStation> {
            return (1..STATIONS_MUL).toList().map {
                ReservationStation(Tag(BaseOperation.M, it))
            }
        }
    }
}
