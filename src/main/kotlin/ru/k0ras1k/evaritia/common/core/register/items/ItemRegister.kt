package ru.k0ras1k.evaritia.common.core.register.items

class ItemRegister(): ItemRegisterHelper() {
    fun register() {
        registerNeutronUpgrade("upgrade_neutron_stack")
        registerNeutronUpgrade("upgrade_neutron_meta")
        registerNeutronUpgrade("upgrade_neutron_time")
    }
}