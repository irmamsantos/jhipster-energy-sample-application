import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';

@Component({
    selector: 'jhi-energy-contracted-power-rating-data-detail',
    templateUrl: './energy-contracted-power-rating-data-detail.component.html'
})
export class EnergyContractedPowerRatingDataDetailComponent implements OnInit {
    energyContractedPowerRatingData: IEnergyContractedPowerRatingData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyContractedPowerRatingData }) => {
            this.energyContractedPowerRatingData = energyContractedPowerRatingData;
        });
    }

    previousState() {
        window.history.back();
    }
}
