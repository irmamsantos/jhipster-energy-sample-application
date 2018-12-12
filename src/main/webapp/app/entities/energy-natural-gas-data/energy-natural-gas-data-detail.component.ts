import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';

@Component({
    selector: 'jhi-energy-natural-gas-data-detail',
    templateUrl: './energy-natural-gas-data-detail.component.html'
})
export class EnergyNaturalGasDataDetailComponent implements OnInit {
    energyNaturalGasData: IEnergyNaturalGasData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyNaturalGasData }) => {
            this.energyNaturalGasData = energyNaturalGasData;
        });
    }

    previousState() {
        window.history.back();
    }
}
