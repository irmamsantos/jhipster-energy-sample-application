import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserSAC } from 'app/shared/model/user-sac.model';

@Component({
    selector: 'jhi-user-sac-detail',
    templateUrl: './user-sac-detail.component.html'
})
export class UserSACDetailComponent implements OnInit {
    userSAC: IUserSAC;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userSAC }) => {
            this.userSAC = userSAC;
        });
    }

    previousState() {
        window.history.back();
    }
}
