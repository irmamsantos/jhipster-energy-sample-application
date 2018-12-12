import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserSAC } from 'app/shared/model/user-sac.model';

type EntityResponseType = HttpResponse<IUserSAC>;
type EntityArrayResponseType = HttpResponse<IUserSAC[]>;

@Injectable({ providedIn: 'root' })
export class UserSACService {
    public resourceUrl = SERVER_API_URL + 'api/user-sacs';

    constructor(private http: HttpClient) {}

    create(userSAC: IUserSAC): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userSAC);
        return this.http
            .post<IUserSAC>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(userSAC: IUserSAC): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userSAC);
        return this.http
            .put<IUserSAC>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUserSAC>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUserSAC[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(userSAC: IUserSAC): IUserSAC {
        const copy: IUserSAC = Object.assign({}, userSAC, {
            updateDate: userSAC.updateDate != null && userSAC.updateDate.isValid() ? userSAC.updateDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.updateDate = res.body.updateDate != null ? moment(res.body.updateDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((userSAC: IUserSAC) => {
                userSAC.updateDate = userSAC.updateDate != null ? moment(userSAC.updateDate) : null;
            });
        }
        return res;
    }
}
