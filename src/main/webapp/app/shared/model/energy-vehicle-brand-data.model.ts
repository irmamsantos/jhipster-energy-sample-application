import { IEnergyVehicleModelData } from 'app/shared/model//energy-vehicle-model-data.model';

export interface IEnergyVehicleBrandData {
    id?: number;
    description?: string;
    models?: IEnergyVehicleModelData[];
}

export class EnergyVehicleBrandData implements IEnergyVehicleBrandData {
    constructor(public id?: number, public description?: string, public models?: IEnergyVehicleModelData[]) {}
}
