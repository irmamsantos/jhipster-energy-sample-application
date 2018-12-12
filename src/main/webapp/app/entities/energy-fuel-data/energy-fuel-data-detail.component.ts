import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyFuelData } from 'app/shared/model/energy-fuel-data.model';

@Component({
    selector: 'jhi-energy-fuel-data-detail',
    templateUrl: './energy-fuel-data-detail.component.html'
})
export class EnergyFuelDataDetailComponent implements OnInit {
    energyFuelData: IEnergyFuelData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyFuelData }) => {
            this.energyFuelData = energyFuelData;
        });
    }

    previousState() {
        window.history.back();
    }
}
