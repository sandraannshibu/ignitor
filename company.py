from flask import * 
from database import*
import uuid




company=Blueprint('company',__name__)

@company.route('/company_home')
def company_home():

	return render_template('company_home.html')

@company.route('/company_managevehicle',methods=['post','get'])
def company_managevehicle():
	data={}
	q="select * from vehicles"
	res=select(q)
	data['vehicleview']=res


	if "action" in request.args:
		action=request.args['action']
		vid=request.args['vid']

	else:action=None

	if action=='delete':
		q="delete from vehicles where vehicle_id='%s'"%(vid)
		delete(q)
		flash('successfully')
		return redirect(url_for('company.company_managevehicle'))

	if action=='update':
		q="select * from vehicles where vehicle_id='%s'"%(vid)
		res=select(q)
		data['vehicleupdate']=res

	if "veupdate" in request.form:
		f=request.form['fname']
		d=request.form['desc']
		a=request.form['amount']

		q="update vehicles set vehicle='%s', `desc`='%s',amount='%s' where vehicle_id='%s'"%(f,d,a,vid)
		update(q)
		print(q)
		flash('successfully')
		return redirect(url_for('company.company_managevehicle'))

	if "vehicle" in request.form:
		cid=session['company_id']
		f=request.form['fname']
		d=request.form['desc']
		a=request.form['amount']
		img=request.files['image']
		path1="static/image"+str(uuid.uuid4())+img.filename
		img.save(path1)
		dofpurchase=request.form['dofpurchase']
		svdate=request.form['svdate']

		# video=request.files['video']
		# path2="static/image"+str(uuid.uuid4())+video.filename
		# video.save(path2)
		q="insert into vehicles values(null,'%s','%s','%s','%s','%s','%s','%s')"%(cid,f,d,a,path1,dofpurchase,svdate)
		insert(q)
		flash('successfully')
		return redirect(url_for('company.company_managevehicle'))
		

	return render_template('company_managevehicle.html',data=data)

@company.route('/company_vehiclebooking')
def company_vehiclebooking():
	data={}
	cid=session['company_id']
	q="SELECT *,CONCAT(request.amount) AS ramo FROM `request` INNER JOIN vehicles USING (vehicle_id) INNER JOIN USER USING (user_id) where company_id='%s'"%(cid)
	res=select(q)
	data['bookingview']=res


	if "action" in request.args:
		action=request.args['action']
		rid=request.args['rid']

		if action=='deliver':
			q="update request set status='deliver' where request_id='%s'"%(rid)
			update(q)
			flash('successfully')
			return redirect(url_for('company.company_vehiclebooking'))


		if action=='outfordeliver':
			q="update request set status='outfordeliver' where request_id='%s'"%(rid)
			update(q)
			print(q)

			
			flash('successfully')
			return redirect(url_for('company.company_vehiclebooking'))
	return render_template('company_vehiclebooking.html',data=data)


@company.route('/company_viewpayment')
def company_viewpayment():
	data={}
	rid=request.args['rid']
	q="SELECT * FROM `payment` INNER JOIN `request` ON `payment`.`requested_id`=`request`.`request_id`where request_id='%s' and requestedfor='Vehicle request'"%(rid)
	res=select(q)
	data['paymentview']=res
	return render_template('company_viewpayment.html',data=data)


@company.route('/viewvehicledetails')
def viewvehicledetails():
	data={}
	rid=request.args['rid']
	q="select * from vehicles where vehicle_id='%s'"%(rid)
	res=select(q)
	data['paymentview']=res
	return render_template('viewvehicledetails.html',data=data)


