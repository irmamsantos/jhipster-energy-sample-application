import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';

@Component({
    selector: 'jhi-energy-vehicle-model-data-detail',
    templateUrl: './energy-vehicle-model-data-detail.component.html'
})
export class EnergyVehicleModelDataDetailComponent implements OnInit {
    energyVehicleModelData: IEnergyVehicleModelData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyVehicleModelData }) => {
            this.energyVehicleModelData = energyVehicleModelData;
        });
    }

    previousState() {
        window.history.back();
    }
}
