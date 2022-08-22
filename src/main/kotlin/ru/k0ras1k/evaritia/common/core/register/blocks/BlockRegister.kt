package ru.k0ras1k.evaritia.common.core.register.blocks

class BlockRegister: BlockRegisterHelper() {
    fun register() {
        registerStandartNeutron(100, "standart_neutron_1")
        registerStandartNeutron(80, "standart_neutron_2")
        registerStandartNeutron(60, "standart_neutron_3")
        registerStandartNeutron(40, "standart_neutron_4")
        registerStandartNeutron(20, "standart_neutron_5")
    }
}