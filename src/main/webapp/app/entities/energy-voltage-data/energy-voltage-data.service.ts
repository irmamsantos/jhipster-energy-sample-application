import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';

type EntityResponseType = HttpResponse<IEnergyVoltageData>;
type EntityArrayResponseType = HttpResponse<IEnergyVoltageData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyVoltageDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-voltage-data';

    constructor(private http: HttpClient) {}

    create(energyVoltageData: IEnergyVoltageData): Observable<EntityResponseType> {
        return this.http.post<IEnergyVoltageData>(this.resourceUrl, energyVoltageData, { observe: 'response' });
    }

    update(energyVoltageData: IEnergyVoltageData): Observable<EntityResponseType> {
        return this.http.put<IEnergyVoltageData>(this.resourceUrl, energyVoltageData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyVoltageData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyVoltageData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
