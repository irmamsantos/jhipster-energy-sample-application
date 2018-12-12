import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';

type EntityResponseType = HttpResponse<IEnergyVehicleModelData>;
type EntityArrayResponseType = HttpResponse<IEnergyVehicleModelData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyVehicleModelDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-vehicle-model-data';

    constructor(private http: HttpClient) {}

    create(energyVehicleModelData: IEnergyVehicleModelData): Observable<EntityResponseType> {
        return this.http.post<IEnergyVehicleModelData>(this.resourceUrl, energyVehicleModelData, { observe: 'response' });
    }

    update(energyVehicleModelData: IEnergyVehicleModelData): Observable<EntityResponseType> {
        return this.http.put<IEnergyVehicleModelData>(this.resourceUrl, energyVehicleModelData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyVehicleModelData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyVehicleModelData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
