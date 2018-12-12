import { Moment } from 'moment';
import { IEnergyNeedNGRequest } from 'app/shared/model//energy-need-ng-request.model';
import { IEnergyNRStateHistory } from 'app/shared/model//energy-nr-state-history.model';

export interface IUserSAC {
    id?: number;
    userSACId?: number;
    userName?: string;
    userEmail?: string;
    updateDate?: Moment;
    needNGRequests?: IEnergyNeedNGRequest[];
    stateHistories?: IEnergyNRStateHistory[];
}

export class UserSAC implements IUserSAC {
    constructor(
        public id?: number,
        public userSACId?: number,
        public userName?: string,
        public userEmail?: string,
        public updateDate?: Moment,
        public needNGRequests?: IEnergyNeedNGRequest[],
        public stateHistories?: IEnergyNRStateHistory[]
    ) {}
}
