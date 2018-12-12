import { IEnergyVehicleBrandData } from 'app/shared/model//energy-vehicle-brand-data.model';

export interface IEnergyVehicleModelData {
    id?: number;
    description?: string;
    energyVehicleBrandData?: IEnergyVehicleBrandData;
}

export class EnergyVehicleModelData implements IEnergyVehicleModelData {
    constructor(public id?: number, public description?: string, public energyVehicleBrandData?: IEnergyVehicleBrandData) {}
}
