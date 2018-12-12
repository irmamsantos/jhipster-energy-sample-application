import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';

@Component({
    selector: 'jhi-energy-vehicle-brand-data-detail',
    templateUrl: './energy-vehicle-brand-data-detail.component.html'
})
export class EnergyVehicleBrandDataDetailComponent implements OnInit {
    energyVehicleBrandData: IEnergyVehicleBrandData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyVehicleBrandData }) => {
            this.energyVehicleBrandData = energyVehicleBrandData;
        });
    }

    previousState() {
        window.history.back();
    }
}
