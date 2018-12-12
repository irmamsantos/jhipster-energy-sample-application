export interface IEnergyVehicleModelData {
    id?: number;
    description?: string;
    energyVehicleBrandDataId?: number;
    brandId?: number;
}

export class EnergyVehicleModelData implements IEnergyVehicleModelData {
    constructor(public id?: number, public description?: string, public energyVehicleBrandDataId?: number, public brandId?: number) {}
}
