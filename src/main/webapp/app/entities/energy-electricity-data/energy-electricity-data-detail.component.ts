import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';

@Component({
    selector: 'jhi-energy-electricity-data-detail',
    templateUrl: './energy-electricity-data-detail.component.html'
})
export class EnergyElectricityDataDetailComponent implements OnInit {
    energyElectricityData: IEnergyElectricityData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyElectricityData }) => {
            this.energyElectricityData = energyElectricityData;
        });
    }

    previousState() {
        window.history.back();
    }
}
