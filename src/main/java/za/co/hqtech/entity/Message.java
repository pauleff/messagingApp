package za.co.hqtech.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author etimpaul
 * 
 * 
 * ---------------------- COMMENTS ---------------------------------------------
 * 
 * Alright, firstly, you want to try and avoid too much unnecessary code.
 * 
 * From all these below: there are only 2 that are required, @Entity (to identify this class as an entity [required by mysql]) and @Id (to identify a key field)
 * everything else is unnecessary.
 * 
 * 
 *  N.B ... you want to avoid using entities as parameters in your controllers. 
 * It would be preferred to use a modal as some data cannot easily be converted from JSON to java, e.g Dates.
 * 
 * 
 * Also, Try LocalDate | LocalTime | LocalDateTime objects instead of java.util.Date.
 * 
 * 
 */

@Entity
@Table(name = "message_") // optional 

// Thos part i think is the most important to change. The best part about spring boot is that you dont need to script SQL. 
// ALL of these queries are provided by your repository (Please see REPO class)
@NamedQueries({ @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
		@NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
		@NamedQuery(name = "Message.findByDateSent", query = "SELECT m FROM Message m WHERE m.dateSent = :dateSent"),
		@NamedQuery(name = "Message.findByDateRead", query = "SELECT m FROM Message m WHERE m.dateRead = :dateRead"),
		@NamedQuery(name = "Message.findBySentFrom", query = "SELECT m FROM Message m WHERE m.sentFrom = :sentFrom"),
		@NamedQuery(name = "Message.findBySentTo", query = "SELECT m FROM Message m WHERE m.sentTo = :sentTo"),
		@NamedQuery(name = "Message.findByIsRead", query = "SELECT m FROM Message m WHERE m.isRead = :isRead") })
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false) //optional arg
	@Column(name = "id") // optional arg. both not necessary
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "dateSent")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSent;
	@Column(name = "dateRead")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRead;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "sentFrom")
	private String sentFrom;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "sentTo")
	private String sentTo;
	@Basic(optional = false)
	@NotNull
	@Lob
	@Size(min = 1, max = 65535)
	@Column(name = "message")
	private String message;
	@Column(name = "isRead")
	private Boolean isRead;

	// Everything coded below can be replaced with 1 line (above class header) -- @Data (lombok plugin)
	public Message() {
	}

	public Message(Integer id) {
		this.id = id;
	}

	public Message(Integer id, Date dateSent, String sentFrom, String sentTo, String message) {
		this.id = id;
		this.dateSent = dateSent;
		this.sentFrom = sentFrom;
		this.sentTo = sentTo;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public Date getDateRead() {
		return dateRead;
	}

	public void setDateRead(Date dateRead) {
		this.dateRead = dateRead;
	}

	public String getSentFrom() {
		return sentFrom;
	}

	public void setSentFrom(String sentFrom) {
		this.sentFrom = sentFrom;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Message)) {
			return false;
		}
		Message other = (Message) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.message + " " + this.sentFrom + " " + this.sentTo;
	}

}
